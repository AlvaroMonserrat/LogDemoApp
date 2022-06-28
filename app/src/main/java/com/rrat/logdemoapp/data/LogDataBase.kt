package com.rrat.logdemoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Log::class], version = 1, exportSchema = false)
abstract class LogDataBase : RoomDatabase(){
    abstract fun logDao() : LogDao
}