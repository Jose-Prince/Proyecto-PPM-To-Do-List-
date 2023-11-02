package com.example.projecttodolist.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.example.projecttodolist.Functions.DrawShape
import com.example.projecttodolist.Navigation.BottomBarScreens

@Composable
fun DailyCalendar(date : String, navController: NavController){

    Box {
        DrawShape(shape = RectangleShape)
    }
    Row (modifier = Modifier.fillMaxWidth()){
        IconButton(
            onClick = {
                navController.navigate(route = BottomBarScreens.Calendar.route)
            },
            modifier = Modifier.size(50.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Regresar",
                modifier = Modifier.size(50.dp),
                tint = Color.White
            )
        }
    }
}

@Preview
@Composable
fun DailyPrev() {
    val navController = rememberNavController()
    DailyCalendar("01/11/2023", navController)
}