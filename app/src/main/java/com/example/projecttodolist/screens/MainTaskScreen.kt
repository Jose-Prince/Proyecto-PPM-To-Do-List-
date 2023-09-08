package com.example.projecttodolist.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.projecttodolist.Navigation.BarNavigation
import com.example.projecttodolist.Navigation.BottomBarScreens
import com.example.projecttodolist.ui.theme.darkblue
import com.example.projecttodolist.ui.theme.gray

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainTaskScreen(navController: NavController){
    val navController = rememberNavController()

    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(gray)
    ){
        Scaffold (
            bottomBar = { BottomBar(navController = navController)},
            drawerBackgroundColor = gray,
            modifier = Modifier.background(Color.Red),
            drawerContentColor = gray
        ){
            BarNavigation(navController = navController)
        }
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

    BottomNavigation (
        modifier = Modifier.background(darkblue),
        backgroundColor = darkblue
    ){
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
                contentDescription = screen.title,
                tint = Color.White)
        })
}

@Preview
@Composable
fun BarPreview() {
    MainTaskScreen(navController = rememberNavController())
}