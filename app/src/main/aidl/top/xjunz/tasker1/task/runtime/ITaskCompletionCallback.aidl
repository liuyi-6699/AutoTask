// ITaskCompletionCallback.aidl
package top.xjunz.tasker1.task.runtime;

// Declare any non-default types here with import statements

interface ITaskCompletionCallback {

    void onTaskCompleted(boolean isSuccessful);

}