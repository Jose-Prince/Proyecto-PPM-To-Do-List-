package com.example.projecttodolist

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.projecttodolist.items_menu.*
import com.example.projecttodolist.screens.*

@Composable 
fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = PantallaActivities.ruta,
        ){
        composable(PantallaActivities.ruta ){
            DisplayActivities()
        }
        composable (PantallaConfiguracion.ruta){
            Configuracion()
        }
    }
}