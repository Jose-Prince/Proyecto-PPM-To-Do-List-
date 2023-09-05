package com.example.projecttodolist

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

import com.example.projecttodolist.items_menu.*

class MainScreen : ComponentActivity() {

    fun OnCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContent {

        }
    }
}

@Composable
@Preview(showBackground = true)
fun MainScrPrev() {

}

fun MainScr() {

}

@Composable
fun DrawShapeM(
    shape: Shape,
    width: Dp = 500.dp,
    height: Dp = 50.dp
) {
    Column (modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.TopCenter)) {
        Box (modifier = Modifier
            .size(width, height)
            .clip(shape)
            .background(color = "#f64836".color))
    }
}

/*@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PantallaPrincipal() {
    val navController = rememberNavController()

    val navigation_item = listOf(
        PantallaActivities,
        PantallaConfiguracion
    )

    Scaffold(
        bottomBar = {
            NavegacionInferior(
                navController = navController,
                menu_items = navigation_item
            )
        }
    ) {
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
    navController : NavHostController,
    menu_items : List<items_menu>
){
    BottomAppBar {

    }
}*/



