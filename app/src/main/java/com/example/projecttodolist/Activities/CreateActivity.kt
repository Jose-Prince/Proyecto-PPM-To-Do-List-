package com.example.projecttodolist.Activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.projecttodolist.Navigation.AppNavigation
import com.example.projecttodolist.Navigation.AppScreens
import com.example.projecttodolist.ui.theme.JetPackComposeTheme
import com.example.projecttodolist.ui.theme.ProjectToDoListTheme

class CreateActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectToDoListTheme {
                // A surface container using the 'background' color from the theme
                JetPackComposeTheme(
                    darkTheme = false
                ) {
                    AppNavigation()
                }
            }
        }
    }
}
@Composable
fun Create(navController: NavController) {
    val navController = rememberNavController()
    DrawShape(shape = RectangleShape)
    IconButton(
        onClick = { navController.navigate(route = AppScreens.Bar.route) },
        modifier = Modifier.size(50.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Regresar",
            modifier = Modifier.size(50.dp),
            tint = Color.White)
    }
}

