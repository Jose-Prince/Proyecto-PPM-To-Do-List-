package com.example.projecttodolist.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.projecttodolist.Activities.DrawShape
import com.example.projecttodolist.GlobalVariables
import com.example.projecttodolist.R
import com.example.projecttodolist.ui.theme.darkblue
import com.example.projecttodolist.ui.theme.gray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings() {

    var nombre by remember { mutableStateOf("")}
    var apellido by remember { mutableStateOf("")}
    var edad by remember { mutableStateOf("")}
    var nacimiento by remember { mutableStateOf("")}
    Column(modifier = Modifier
        .background(gray)
        .fillMaxSize()) {
        Row{
            Text(text = "Nombre:")
            Text(text = "Apellido:")
        }
        Row {
            TextField(value = nombre, onValueChange = {nombre = it},
                modifier = Modifier.padding(16.dp)
                    .border(1.dp, darkblue)
                    .padding(8.dp),
                singleLine = true
                )
            TextField(value = apellido, onValueChange = {apellido = it},
                modifier = Modifier.padding(16.dp)
                    .border(1.dp, darkblue)
                    .padding(8.dp)
                    .width(50.dp),
                singleLine = true
            )
        }
        Row{
            Text(text = "Edad: ")
            Text(text = "Fecha de nacimiento: ")
        }
        Row{
            TextField(value = edad, onValueChange = {edad = it},
                modifier = Modifier.padding(16.dp)
                    .border(1.dp, darkblue)
                    .padding(8.dp),
                singleLine = true
            )
            TextField(value = nacimiento, onValueChange = {nacimiento = it},
                modifier = Modifier.padding(16.dp)
                    .border(1.dp, darkblue)
                    .padding(8.dp),
                singleLine = true
            )
        }
    }
}

@Preview
@Composable
fun SettingsPrev() {
    Settings()
}