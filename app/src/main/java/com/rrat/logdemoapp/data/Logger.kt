package com.rrat.logdemoapp.data

import android.os.Handler
import android.os.Looper
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Logger(private val logDao: LogDao) {

    //Maximum 4 threads will be active to process tasks.

    //If more than 4 threads are submitted then they are held in a queue
    // until threads become available.

    private val executorService: ExecutorService = Executors.newFixedThreadPool(4)


    private val mainThreadHandler by lazy {
        Handler(Looper.getMainLooper())
    }

    fun addLog(msg: String)
    {
        executorService.execute{
            logDao.insertAll(Log(msg, System.currentTimeMillis()))
        }
    }

    fun getAllLogs(callback: (List<Log>)->Unit)
    {
        executorService.execute{
            val logs = logDao.getAll()
            mainThreadHandler.post{
                callback(logs)
            }
        }
    }

    fun removeLogs()
    {
        executorService.execute{
            logDao.nukeTable()
        }
    }
}