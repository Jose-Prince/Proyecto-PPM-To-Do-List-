package com.example.projecttodolist.Navigation

import com.example.projecttodolist.R

sealed class AppScreens(val route : String) {
    object  LogInScreen: AppScreens("Log_in")
    object Bar: AppScreens("task")
    object Create: AppScreens("create")
    object Show: AppScreens("show")
}
