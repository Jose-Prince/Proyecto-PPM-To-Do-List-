package com.example.projecttodolist.Navigation

sealed class AppScreens(val route : String) {
    object  LogInScreen: AppScreens("Log_in")
    object TaskScreen: AppScreens("task")
}
