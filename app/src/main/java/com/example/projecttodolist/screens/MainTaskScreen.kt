package com.example.projecttodolist.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.projecttodolist.DrawShape
import com.example.projecttodolist.scr

@Composable
fun MainTaskScreen(navController: NavController) {
    Box (modifier = Modifier.fillMaxSize()){
        scr()
        Box {
            DrawShape(shape = RectangleShape)
        }
    }
}
