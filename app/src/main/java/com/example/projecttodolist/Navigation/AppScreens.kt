package com.example.projecttodolist.Navigation

sealed class AppScreens(val route : String) {

    object  LogInScreen: AppScreens("Log_in")

    //Pantalla de bottom bar navigation
    object Bar: AppScreens("task")

    //Pantalla de creación de tareas
    object Create: AppScreens("create")

    //Pantalla de muestra de tareas
    object Show: AppScreens("show")
}
