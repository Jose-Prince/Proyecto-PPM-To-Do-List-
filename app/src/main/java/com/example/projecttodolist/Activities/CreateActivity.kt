package com.example.projecttodolist.Activities

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.projecttodolist.screens.MainTaskScreen
import java.util.Calendar
import java.util.Date

class CreateActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            Create(navController)
        }
    }
}
@Composable
fun Create(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var start by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    var maxCharacters : Int = 36
    val context = LocalContext.current
    DrawShape(shape = RectangleShape)
    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        IconButton(
            onClick = {
                val intent = Intent(context, MainTaskScreen::class.java)
                context.startActivity(intent)
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

        Text(text = "Nombre de la tarea:")
        TextField(
            value = name,
            onValueChange = { newText ->
                if (newText.length <= maxCharacters)
                    name = newText
            }
        )
        Text(text = "Ejecución: ")
        showDatePicker(context)


        Text(text = "Hora de inicio:")
        TextField(
            value = start,
            onValueChange = { newText ->
                if (newText.length <= maxCharacters)
                    name = newText })
        Text(text = "Descripción: ")
        TextField(
            value = desc,
            onValueChange = { newText ->
                if (newText.length <= maxCharacters)
                    name = newText })
    }
}

@Preview
@Composable
fun CreatePrev() {
    val navController = rememberNavController()
    Create(navController)
}

@Composable
fun showDatePicker(context: Context){
    var buttonText by remember { mutableStateOf("??/??/????") }
    var condition by remember { mutableStateOf(false) }

    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val date = remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(
        context,
        {_: DatePicker, year : Int, month : Int, dayOfMonth : Int ->
            date.value = "$dayOfMonth/$month/$year"
            buttonText = date.value
        }, year, month, day
    )

    Text(text = "Fecha de ejecución: ")
    TextButton(onClick = { datePickerDialog.show() }) {
        Text(text = buttonText)
    }
}

