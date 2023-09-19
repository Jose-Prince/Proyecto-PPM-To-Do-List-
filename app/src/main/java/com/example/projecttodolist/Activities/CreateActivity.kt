package com.example.projecttodolist.Activities

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import com.example.projecttodolist.GlobalVariables
import com.example.projecttodolist.Tarea
import com.example.projecttodolist.idCreator
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

        var date = showDatePicker(context)
        
        var time = showTimePicker()

        Text(text = "Descripción: ")
        TextField(
            value = desc,
            onValueChange = { newText ->
                if (newText.length <= maxCharacters)
                    name = newText })
        
        Button(onClick = {
            var task = Tarea()
            task.id = idCreator()
            task.name = name
            task.date = date
            task.time = time.toString()

            GlobalVariables.listOfTasks.add(task)

            val intent = Intent(context, MainTaskScreen::class.java)
            context.startActivity(intent)


        }) {
            Text(text = "Crear")
        }
    }
}

@Preview
@Composable
fun CreatePrev() {
    val navController = rememberNavController()
    Create(navController)
}

@Composable
fun showDatePicker(context: Context) : String{
    var buttonText by remember { mutableStateOf("??/??/????") }

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
    return buttonText
}

@Composable
fun showTimePicker(): MutableState<String> {
    val mContext = LocalContext.current
    
    val calendar = Calendar.getInstance()
    val hour = calendar[Calendar.HOUR_OF_DAY]
    val minute = calendar[Calendar.MINUTE]
    
    val time = remember { mutableStateOf("") }
    
    val timePickerDialog = TimePickerDialog(
        mContext,
        {_, hour : Int, minute : Int ->
            val timeSuffix = if (hour < 12) "AM" else "PM"
            val formattedHour = if (hour > 12) hour - 12 else hour
            val formattedMinute = if (minute < 10) "0$minute" else minute
            time.value = "$formattedHour:$formattedMinute $timeSuffix"
            }, hour, minute, false
    )
    Text(text = "Hora de inicio: ${time.value}")
    Button(onClick = { timePickerDialog.show() }) {
        Text(text = "Seleccionar hora")
    }
    return time
}

