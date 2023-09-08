package com.example.projecttodolist.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projecttodolist.screens.CreateTask
import com.example.projecttodolist.screens.LogInScreen
import com.example.projecttodolist.screens.MainTaskScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.LogInScreen.route,
        ){
        composable(route = AppScreens.LogInScreen.route){
            LogInScreen(navController)
        }
        composable(route = AppScreens.TaskScreen.route){
            MainTaskScreen(navController)
        }
        composable(route = AppScreens.CreateTaskScreen.route){
            CreateTask(navController)
        }
    }
}