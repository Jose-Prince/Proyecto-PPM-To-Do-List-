package com.example.projecttodolist.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projecttodolist.Activities.Create
import com.example.projecttodolist.screens.LogInScreen
import com.example.projecttodolist.screens.MainTaskScreen
import com.example.projecttodolist.screens.RegisterScreen
import com.example.projecttodolist.screens.Show

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
        composable(route = AppScreens.Bar.route){
            MainTaskScreen(navController)
        }
        composable(route = AppScreens.Create.route){
            Create(navController)
        }
        composable(route = AppScreens.Show.route){
            Show()
        }
        composable(route = AppScreens.RegisterScreen.route){
            RegisterScreen(navController)
        }
    }
}