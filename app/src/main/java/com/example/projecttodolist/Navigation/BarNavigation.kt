package com.example.projecttodolist.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.projecttodolist.GlobalVariables
import com.example.projecttodolist.screens.Calendar
import com.example.projecttodolist.screens.DailyCalendar
import com.example.projecttodolist.screens.Settings
import com.example.projecttodolist.screens.Show

@Composable
fun BarNavigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = BottomBarScreens.ShowTasks.route,
        ){

        composable(route = BottomBarScreens.ShowTasks.route){
            GlobalVariables.showTopBar = true
            GlobalVariables.showFloatingButton = true
            Show()
        }

        composable(route = BottomBarScreens.Settings.route){
            GlobalVariables.showTopBar = true
            GlobalVariables.showFloatingButton = false
            Settings()
        }

        composable(route = BottomBarScreens.Calendar.route) {
            GlobalVariables.showTopBar = false
            GlobalVariables.showFloatingButton = true
            Calendar(navController)
        }
        
        composable(route = BottomBarScreens.DailyCalendar.route) {
            DailyCalendar(date = GlobalVariables.date, navController)
        }
    }
}