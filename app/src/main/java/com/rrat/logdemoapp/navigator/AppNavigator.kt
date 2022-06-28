package com.rrat.logdemoapp.navigator


enum class SCREENS{
    MENU,
    LOGS
}

interface AppNavigator {
    fun navigateTo(screens: SCREENS)
}