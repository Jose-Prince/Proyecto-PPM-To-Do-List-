package com.example.projecttodolist

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.projecttodolist.items_menu.*
import com.example.projecttodolist.ui.theme.ProjectToDoListTheme

class MainScreen : ComponentActivity() {

    fun OnCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContent {
            ProjectToDoListTheme {
                Surface (modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background)
                {
                    MainScr()
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MainScrPrev() {
    MainScr()
}

@Composable
fun MainScr() {
    Box (modifier = Modifier.fillMaxSize()){
        scr()
        Box {
            DrawShape(shape = RectangleShape)
        }
    }
}



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun scr() {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()

    val navigation_item = listOf(
        PantallaActivities,
        PantallaConfiguracion
    )

    Scaffold (
        bottomBar = {}
        ){
        NavigationHost(navController)
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val entrada by navController.currentBackStackEntryAsState()
    return entrada?.destination?.route
}

@Composable
fun NavegacionInferior(
    navController: NavHostController,
    menu_items : List<items_menu>
){
    BottomAppBar {
        BottomNavigation {
            val currentRoute = currentRoute(navController = navController)
            menu_items.forEach { item ->
                BottomNavigationItem(
                    selected = currentRoute == item.ruta,
                    onClick = { navController.navigate(item.ruta) },
                    icon = { Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title) })
            }
        }
    }
}






