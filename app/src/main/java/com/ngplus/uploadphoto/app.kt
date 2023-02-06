package com.ngplus.uploadphoto

import android.app.Application
import timber.log.Timber
import timber.log.Timber.*


class app: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }
}