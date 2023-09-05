package com.example.projecttodolist.screens

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projecttodolist.DrawShape
import com.example.projecttodolist.EditActivity
import com.example.projecttodolist.MainScreen
import com.example.projecttodolist.Navigation.AppScreens
import com.example.projecttodolist.color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(navController: NavController) {
    var text by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("") }
    var maxCharacters : Int = 36
    Column (modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.TopCenter),
        verticalArrangement = Arrangement.Center){
        DrawShape(shape = RectangleShape)
        Spacer(modifier = Modifier.height(125.dp))

        Text(
            text = "Usuario:",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth())
        Row {
            Spacer(modifier = Modifier.width(55.dp))
            OutlinedTextField(
                value = text,
                onValueChange = {newText ->
                    if (newText.length <= maxCharacters)
                        text = newText
                },
                modifier = Modifier
                    .border(width = 2.dp, color = "#f8a29e".color, RoundedCornerShape(32.dp))
                    .background(color = "#f8a29e".color, shape = RoundedCornerShape(32.dp)),
                shape = RoundedCornerShape(32.dp),
                textStyle = TextStyle(textAlign = TextAlign.Center,
                    fontSize = 21.sp)
            )

        }
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Contraseña:",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Row {
            Spacer(modifier = Modifier.width(55.dp))
            OutlinedTextField(
                value = text2,
                onValueChange = {newText ->
                    if (newText.length <= maxCharacters)
                        text2 = newText
                },
                modifier = Modifier
                    .border(width = 2.dp, color = "#f8a29e".color, RoundedCornerShape(32.dp))
                    .background(color = "#f8a29e".color, shape = RoundedCornerShape(32.dp)),
                shape = RoundedCornerShape(32.dp),
                singleLine = true,
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 21.sp
                )
            )
        }
        Spacer(modifier = Modifier.height(70.dp))
        Row {
            Spacer(modifier = Modifier.width(113.dp))
            Button(onClick = {
                             navController.navigate(route = AppScreens.TaskScreen.route)
            },
            ) {
                Text(text = "Iniciar sesión",
                    fontSize = 21.sp)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "     ─────────── O ───────────     ",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(10.dp))
        Row (modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)){
            Spacer(modifier = Modifier.width(117.dp))
            Button(
                onClick = { },
                //modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(text = "Resgistrarse",
                    fontSize = 21.sp)
            }
        }
    }
}
