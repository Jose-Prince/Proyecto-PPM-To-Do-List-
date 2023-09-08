package com.example.projecttodolist.screens

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.projecttodolist.DrawShape
import com.example.projecttodolist.Navigation.AppScreens

@Composable
fun CreateTask(navController: NavController) {
    val navController = rememberNavController()
    DrawShape(shape = RectangleShape)
    IconButton(
        onClick = { navController.navigate(route = AppScreens.TaskScreen.route) },
        modifier = Modifier.size(50.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Regresar",
            modifier = Modifier.size(50.dp),
            tint = Color.White)
    }
}

