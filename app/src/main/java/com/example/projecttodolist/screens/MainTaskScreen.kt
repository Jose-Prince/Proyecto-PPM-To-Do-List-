package com.example.projecttodolist.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.projecttodolist.Navigation.BarNavigation
import com.example.projecttodolist.Navigation.BottomBarScreens

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainTaskScreen(){
    val navController = rememberNavController()
    Scaffold (
        bottomBar = { BottomBar(navController = navController)}
    ){
        BarNavigation(navController = navController)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreens.ShowTasks,
        BottomBarScreens.Settings
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController)
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreens,
    currentDestination: NavDestination?,
    navController: NavHostController
){
    BottomNavigationItem(
        selected = currentDestination?.hierarchy?.any {
              it.route == screen.route
        } == true,
        onClick = { navController.navigate(screen.route) },
        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = screen.title)
        })
}