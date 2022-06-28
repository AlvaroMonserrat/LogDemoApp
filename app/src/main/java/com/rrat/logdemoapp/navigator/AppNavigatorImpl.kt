package com.rrat.logdemoapp.navigator

import androidx.fragment.app.FragmentActivity
import com.rrat.logdemoapp.R
import com.rrat.logdemoapp.ui.LogsFragment
import com.rrat.logdemoapp.ui.MenuFragment

class AppNavigatorImpl(private val activity: FragmentActivity) : AppNavigator {

    override fun navigateTo(screens: SCREENS) {
        val fragment = when(screens)
        {
            SCREENS.MENU -> MenuFragment()
            SCREENS.LOGS -> LogsFragment()
        }

        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .addToBackStack(fragment::class.java.canonicalName)
            .commit()
    }


}