package com.example.projecttodolist.screens

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.projecttodolist.Activities.Create
import com.example.projecttodolist.Activities.CreateActivity
import com.example.projecttodolist.Activities.DrawShape
import com.example.projecttodolist.GlobalVariables
import com.example.projecttodolist.Navigation.BarNavigation
import com.example.projecttodolist.Navigation.BottomBarScreens
import com.example.projecttodolist.R
import com.example.projecttodolist.TaskViewModel
import com.example.projecttodolist.ui.theme.blue
import com.example.projecttodolist.ui.theme.darkblue
import com.example.projecttodolist.ui.theme.gray
import com.example.projecttodolist.ui.theme.green
import kotlinx.coroutines.CoroutineScope

class MainTaskScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MainTaskScreen(navController)
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainTaskScreen(navController: NavController){
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(gray)
    ){

        Scaffold (
            topBar = {if (GlobalVariables.showTopBar){ Column (horizontalAlignment = Alignment.CenterHorizontally){
                DrawShape(shape = RectangleShape)
                Spacer(modifier = Modifier.size(15.dp))
                Image(
                    painter = painterResource(id = R.drawable.baseline_person_24),
                    contentDescription = "Imagen de perfil",
                    modifier = Modifier.size(60.dp))
                Text(text = "Nombre de usuario",
                    fontSize = 35.sp)
            }}},
            bottomBar = { BottomBar(navController = navController)},
            scaffoldState = scaffoldState,
            floatingActionButton = { if (GlobalVariables.showFloatingButton) {
                Fab(navController,scope,scaffoldState)
            } },
            backgroundColor = gray
        ){
            BarNavigation(navController = navController)
        }
    }
}

@Composable
fun Fab(navController : NavController,scope : CoroutineScope, scaffoldState: ScaffoldState){
    val context = LocalContext.current
    FloatingActionButton(onClick = {
        val intent = Intent(context, CreateActivity::class.java)
        context.startActivity(intent)
        TaskViewModel::refresh
    },
        containerColor = green,
        modifier = Modifier.size(60.dp),
        shape = CircleShape
        ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null,
            modifier = Modifier.size(45.dp),
            tint = Color.White)
    }
}


@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreens.ShowTasks,
        BottomBarScreens.Calendar,
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
        onClick = { navController.navigate(screen.route)
                  TaskViewModel::refresh},
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