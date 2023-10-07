package com.example.projecttodolist.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.projecttodolist.screens.Settings
import com.example.projecttodolist.screens.Show

@Composable
fun BarNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreens.ShowTasks.route,
        ){
            composable(route = BottomBarScreens.ShowTasks.route){
                Show()
            }

        composable(route = BottomBarScreens.Settings.route){
            Settings()
        }
    }
}