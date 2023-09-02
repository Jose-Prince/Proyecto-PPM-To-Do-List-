package com.example.projecttodolist

import android.graphics.Color.parseColor
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projecttodolist.ui.theme.ProjectToDoListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectToDoListTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    var text by remember { mutableStateOf("")}
    var text2 by remember { mutableStateOf("")}
    var maxCharacters : Int = 36
    Column (modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.TopCenter),
        verticalArrangement = Arrangement.Center){
        DrawShape(shape = RectangleShape)

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
        Row {
            Spacer(modifier = Modifier.width(113.dp))
            Button(
                onClick = { /*TODO*/ },
            ) {
                Text(text = "Iniciar sesión",
                    fontSize = 21.sp)
            }
        }
        Text(text = "     ─────────── O ───────────     ",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth())
        Row (modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)){
            Spacer(modifier = Modifier.width(117.dp))
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(text = "Resgistrarse",
                    fontSize = 21.sp)
            }
        }
            //Spacer(modifier = Modifier.width(135.dp))


    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    ProjectToDoListTheme {
        LoginScreen()
    }
}

@Composable
fun DrawShape(
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

val String.color
    get() = Color(parseColor(this))