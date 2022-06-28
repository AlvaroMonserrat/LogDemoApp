package com.rrat.logdemoapp.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DateFormatter @Inject constructor(){

    @SuppressLint("SimpleDateFormat")
    private val formatter = SimpleDateFormat("d MM yyyy HH:mm:ss")

    fun formatDate(timestamp: Long): String{
        return formatter.format(Date(timestamp))
    }
}