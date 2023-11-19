package com.example.projecttodolist.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.projecttodolist.GlobalVariables
import com.example.projecttodolist.screens.Calendar
import com.example.projecttodolist.screens.Settings
import com.example.projecttodolist.screens.Show
import com.example.projecttodolist.screens.AddContact
import com.example.projecttodolist.screens.ShowCollabs
import com.example.projecttodolist.screens.ShowGroups

@Composable
fun BarNavigation(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = if (GlobalVariables.destination == 0) {
            BottomBarScreens.ShowTasks.route
        } else {
            BottomBarScreens.Calendar.route
        }
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

        composable(route = BottomBarScreens.AddContact.route) {
            GlobalVariables.showTopBar = true
            GlobalVariables.showFloatingButton = false
            AddContact(navController)
        }

        composable(route = BottomBarScreens.ShowCollabs.route) {
            GlobalVariables.showTopBar = true
            GlobalVariables.showFloatingButton = false
            ShowCollabs(navController)
        }

        composable(route = BottomBarScreens.ShowGroups.route) {
            GlobalVariables.showTopBar = true
            GlobalVariables.showFloatingButton = false
            ShowGroups(navController)
        }
    }
}
