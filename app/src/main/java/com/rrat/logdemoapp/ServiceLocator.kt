package com.rrat.logdemoapp

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.room.Room
import com.rrat.logdemoapp.data.LogDataBase
import com.rrat.logdemoapp.data.Logger
import com.rrat.logdemoapp.navigator.AppNavigator
import com.rrat.logdemoapp.navigator.AppNavigatorImpl
import com.rrat.logdemoapp.util.DateFormatter

class ServiceLocator(applicationContext: Context) {

    private val logDatabase = Room.databaseBuilder(
        applicationContext,
        LogDataBase::class.java,
        "log.db")
        .build()

    fun provideLogger() = Logger(logDatabase.logDao())

}