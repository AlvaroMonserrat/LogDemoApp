package com.rrat.logdemoapp.application

import android.app.Application
import android.util.Log
import com.rrat.logdemoapp.ServiceLocator

class LogApplication : Application(){

    lateinit var serviceLocator: ServiceLocator

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "ON CREATE")
        serviceLocator = ServiceLocator(applicationContext)
    }

    override fun onTerminate() {
        Log.i(TAG, "ON TERMINATE")
        super.onTerminate()
    }


    companion object{
        const val TAG = "LOG_APPLICATION"
    }
}