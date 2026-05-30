/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.util

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import rikka.shizuku.Shizuku
import top.xjunz.tasker1.BuildConfig
import top.xjunz.tasker1.R
import top.xjunz.tasker1.app
import top.xjunz.tasker1.ktx.format
import top.xjunz.tasker1.ktx.launchIntentSafely
import top.xjunz.tasker1.ktx.viewUrlSafely

/**
 * @author Mengran 2023/03/06
 */
object Feedbacks {

    fun dumpEnvInfo() = buildString {
        appendLine("Basic:")
        appendLine("--version code = ${BuildConfig.VERSION_CODE}")
        appendLine("--version name = ${BuildConfig.VERSION_NAME}")
        appendLine("--android version = ${Build.VERSION.RELEASE} (${Build.VERSION.SDK_INT})")
        appendLine("--brand & model = ${Build.BRAND} ${Build.MODEL}")
        appendLine("--supported abi = ${Build.SUPPORTED_ABIS.joinToString()}")

        appendLine("Shizuku:")
        val shizukuBinderReceived = Shizuku.pingBinder()
        appendLine("--ping binder = $shizukuBinderReceived")
        if (shizukuBinderReceived) {
            appendLine("--shizuku version = ${Shizuku.getVersion()}")
            appendLine("--uid = ${Shizuku.getUid()}")
        }
    }

    fun addGroup() {
        app.viewUrlSafely("mqqapi://card/show_pslcard?src_type=internal&version=1&uin=1081698830&card_type=group&source=qrcode")
    }

    fun feedbackByEmail(file: Uri?) {
        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:")).putExtra(
            Intent.EXTRA_SUBJECT, R.string.mail_subject.format(formatCurrentTime())
        ).putExtra(Intent.EXTRA_TEXT, R.string.mail_body.format(dumpEnvInfo()))
            .putExtra(Intent.EXTRA_EMAIL, arrayOf("liuyi615708@gmail.com"))
        if (file != null) {
            intent.putExtra(Intent.EXTRA_STREAM, file)
            val resInfoList = app.packageManager.queryIntentActivities(
                intent, PackageManager.MATCH_DEFAULT_ONLY
            )
            for (resolveInfo in resInfoList) {
                val packageName = resolveInfo.activityInfo.packageName
                app.grantUriPermission(packageName, file, Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }
        }
        app.launchIntentSafely(intent)
    }

    fun feedbackErrorByEmail(error: String) {
        val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:")).putExtra(
            Intent.EXTRA_SUBJECT, R.string.mail_subject.format(formatCurrentTime())
        ).putExtra(Intent.EXTRA_TEXT, R.string.mail_body.format(dumpEnvInfo() + "\n" + error))
            .putExtra(Intent.EXTRA_EMAIL, arrayOf("liuyi615708@gmail.com"))
        app.launchIntentSafely(intent)
    }

    fun addTelegram() {
        app.viewUrlSafely("https://t.me/coloros_home")
    }
}
