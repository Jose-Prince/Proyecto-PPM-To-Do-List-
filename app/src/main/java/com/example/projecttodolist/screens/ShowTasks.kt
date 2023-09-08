package com.example.projecttodolist.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projecttodolist.DrawShape
import com.example.projecttodolist.R
import com.example.projecttodolist.ui.theme.gray

@Composable
fun ShowTasks() {
    Box (modifier = Modifier.fillMaxSize().background(gray)){

    }
    Column (modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.TopCenter)
        .background(gray),
        verticalArrangement = Arrangement.Center){
        DrawShape(shape = RectangleShape)
        Image(
            painter = painterResource(id = R.drawable.baseline_person_24),
            contentDescription = "Imagen de perfil")
        Text(text = "Nombre de usuario",
            textAlign = TextAlign.Center)
        Button(
            onClick = { /* Acción del botón */ },
            modifier = Modifier
                .height(56.dp)
                .clip(RoundedCornerShape(64.dp)) // Cambia el valor para ajustar el radio de las esquinas
        ) {
            Text(text = " ")
        }
    }
    
}

@Preview
@Composable
fun TasksPreview() {
    ShowTasks()
}
