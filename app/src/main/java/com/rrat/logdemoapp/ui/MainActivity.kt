package com.rrat.logdemoapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.rrat.logdemoapp.application.LogApplication
import com.rrat.logdemoapp.databinding.ActivityMainBinding
import com.rrat.logdemoapp.navigator.AppNavigator
import com.rrat.logdemoapp.navigator.AppNavigatorImpl
import com.rrat.logdemoapp.navigator.SCREENS
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    @Inject lateinit var navigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.i(TAG, "ON CREATE")
        //navigator = (applicationContext as LogApplication).serviceLocator.provideNavigator(this)
        if(savedInstanceState == null)
        {
            navigator.navigateTo(SCREENS.MENU)
            Log.i(TAG, "saved Instance State is Null")
        }


    }

    companion object{
        const val TAG = "MAIN_ACTIVITY"
    }
}