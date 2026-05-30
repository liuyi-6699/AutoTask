package top.xjunz.tasker1.api

import android.text.format.Formatter
import androidx.core.text.parseAsHtml
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import top.xjunz.tasker1.BuildConfig
import top.xjunz.tasker1.R
import top.xjunz.tasker1.app
import top.xjunz.tasker1.ktx.format
import top.xjunz.tasker1.ktx.str

@Serializable
data class UpdateInfo(
    val binary: Binary,
    val build: String,
    val changelog: String?,
    val direct_install_url: String,
    val installUrl: String,
    val install_url: String,
    val name: String,
    val update_url: String,
    val updated_at: Long,
    val version: String,
    val versionShort: String
) {
    @Serializable
    data class Binary(
        @SerialName("fsize") val size: Long
    )

    fun hasUpdates(): Boolean {
        // 对比GitHub Release的tag_name与当前版本名
        return build != "v" + BuildConfig.VERSION_NAME
    }

    fun formatToString(): CharSequence {
        return R.string.html_updates_info.format(
            versionShort,
            Formatter.formatFileSize(app, binary.size),
            changelog?.replace("\n", "<br>") ?: R.string.nothing_here.str
        ).parseAsHtml()
    }
}