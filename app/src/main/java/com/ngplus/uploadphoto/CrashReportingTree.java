package com.ngplus.uploadphoto;

import android.util.Log;

import androidx.annotation.NonNull;
import timber.log.Timber;

/** A tree which logs important information for crash reporting. */
class CrashReportingTree extends Timber.Tree {
    @Override protected void log(int priority, String tag, @NonNull String message, Throwable t) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return;
        }

        //FakeCrashLibrary.log(priority, tag, message);

        if (t != null) {
            if (priority == Log.ERROR) {
                //FakeCrashLibrary.logError(t);
            } else if (priority == Log.WARN) {
                //FakeCrashLibrary.logWarning(t);
            }
        }
    }
}