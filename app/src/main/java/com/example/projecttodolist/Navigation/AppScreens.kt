package com.example.projecttodolist.Navigation

import com.example.projecttodolist.R

sealed class AppScreens(val route : String) {
    object  LogInScreen: AppScreens("Log_in")
    object TaskScreen: AppScreens("task")
    object CreateTaskScreen: AppScreens("createTask")

}
