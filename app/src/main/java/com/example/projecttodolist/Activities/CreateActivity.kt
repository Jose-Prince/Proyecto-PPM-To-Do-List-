package com.example.projecttodolist.Activities

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.projecttodolist.Functions.*
import com.example.projecttodolist.GlobalVariables
import com.example.projecttodolist.Tarea
import com.example.projecttodolist.dataStorage.StoreUserTask
import com.example.projecttodolist.screens.MainTaskScreen
import com.example.projecttodolist.ui.theme.blue
import com.example.projecttodolist.ui.theme.darkblue
import com.example.projecttodolist.ui.theme.gray
import com.example.projecttodolist.ui.theme.green
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
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
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var desc by remember { mutableStateOf("") }
    val checkedState = remember { mutableStateOf(false)}
    var error by remember { mutableStateOf(false) }
    var height : Int = 100
    var dateI = ""
    var dateF = ""
    var timeI : String = ""
    var timeF : String = ""
    var duration : Long = 1
    val context = LocalContext.current

    val dataStore = StoreUserTask(context)

    var fechaF = remember { mutableStateOf("")}
    var fechaI = remember { mutableStateOf(LocalDate.now()) }

    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val onClickAction : () -> Unit = {
        if (name.text.isBlank()){
            error = true
        } else {
            if (dateI > dateF){
                dateF = dateI
            }
            duration = durationCalc(dateI, dateF)

            var task = Tarea(typeAssignation(GlobalVariables.taskType),name.text, dateI, dateF,timeI,timeF,duration)
            GlobalVariables.listOfTasks.add(task)
            TaskByDate(task)
            organizeTaskInMap(GlobalVariables.listWithAllDates, GlobalVariables.MapTaskDates)
            val intent = Intent(context, MainTaskScreen::class.java)
            context.startActivity(intent)
            scope.launch {
                dataStore.saveTasks(GlobalVariables.listOfTasks)
            }
        }
    }

    Box (modifier = Modifier
        .background(gray)
        .fillMaxSize()){
        DrawShape(shape = RectangleShape)
    }
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


        TextField(
            value = name,
            onValueChange = {
                name = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, if (error) Color.Red else Color.Transparent),
            textStyle = TextStyle(
                color = Color.Black,  // Color del texto ingresado
                fontSize = 23.sp     // Tamaño del texto ingresado
            ),
            placeholder = {
                Text("Título", style = TextStyle(color = Color.Gray, fontSize = 23.sp))
            },
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedLabelColor = Color.Transparent,
                focusedLabelColor = Color.Transparent,
                backgroundColor = Color.Transparent,
                cursorColor = darkblue  // Color del cursor cuando se ingresa texto
            )
        )
        if (error) {
            Text(text = "Debe ingresar un título para la tarea",
                style = TextStyle(color = Color.Red)
            )
        }
        Divider(modifier = Modifier.fillMaxWidth(),
            color = blue)

        Row (modifier = Modifier.fillMaxWidth()){
            Spacer(modifier = Modifier.width(60.dp))
            Text(text = "Inicio",
                modifier = Modifier.padding(10.dp),
                fontSize = 20.sp)
            Spacer(modifier = Modifier.width(64.dp))
            Divider(modifier = Modifier
                .height(50.dp)
                .width(1.dp),
                color = blue)
            Spacer(modifier = Modifier.width(70.dp))
            Text(text = "Fin",
                modifier = Modifier.padding(10.dp),
                fontSize = 20.sp)
        }
        Divider(modifier = Modifier.fillMaxWidth(),
            color = blue)

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            Column (horizontalAlignment = Alignment.CenterHorizontally){
                dateI = showDatePickerI(context, fechaF, fechaI)
                if (!checkedState.value)
                    timeI = showTimePicker()
            }
            Spacer(modifier = Modifier.width(30.dp))
            if (checkedState.value)
                height = 50

            Divider(modifier = Modifier
                .height(height.dp)
                .width(1.dp),
                color = blue)
            Spacer(modifier = Modifier.width(30.dp))
            Column (horizontalAlignment = Alignment.CenterHorizontally){
                dateF = showDatePickerF(context, fechaF, fechaI)
                if (!checkedState.value) {
                    timeF = showTimePicker()
                }
            }
        }
        Divider(modifier = Modifier.fillMaxWidth(),
            color = blue)
        Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
            Text(text = "Todo el día: ", fontSize = 20.sp, modifier = Modifier.padding(start = 10.dp))
            Spacer(modifier = Modifier.width(190.dp))
            Switch(checked = checkedState.value, onCheckedChange = {checkedState. value = it})
        }
        Divider(modifier = Modifier.fillMaxWidth(),
            color = blue)

        TextField(
            value = desc,
            onValueChange = {
                desc = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Transparent),
            textStyle = TextStyle(
                color = Color.Black,  // Color del texto ingresado
                fontSize = 20.sp     // Tamaño del texto ingresado
            ),
            placeholder = {
                Text("Descripción", style = TextStyle(color = Color.Gray, fontSize = 20.sp))
            },
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedLabelColor = Color.Transparent,
                focusedLabelColor = Color.Transparent,
                backgroundColor = Color.Transparent,
                cursorColor = darkblue  // Color del cursor cuando se ingresa texto
            )
        )
    }
    Column (modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.End){
        FabCheck(navController, scope, scaffoldState,onClickAction)
    }
}

@Preview
@Composable
fun CreatePrev() {
    val navController = rememberNavController()
    Create(navController)
}

@Composable
fun showDatePickerI(context: Context, fechaF: MutableState<String>, fechaI: MutableState<LocalDate>) : String{
    val formato = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    var currentDate = LocalDate.now()

    var buttonText by remember { mutableStateOf(currentDate.format(formato)) }

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
            date.value = "$dayOfMonth/${month + 1}/$year"
            buttonText = date.value
        }, year, month, day
    )
    TextButton(onClick = { datePickerDialog.show() }) {
        Text(text = buttonText, fontSize = 20.sp, color = green)
    }
    fechaF.value = buttonText
    return buttonText
}

@Composable
fun showDatePickerF(context: Context, fechaF: MutableState<String>, fechaI: MutableState<LocalDate>) : String{
    val formato = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    var currentDate = LocalDate.now()

    var buttonText by remember { mutableStateOf(currentDate.format(formato)) }

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
            date.value = "$dayOfMonth/${month + 1}/$year"
            buttonText = date.value
        }, year, month, day
    )

    TextButton(onClick = { datePickerDialog.show() }) {
        Text(text = if (LocalDate.parse(fechaF.value, formato) > LocalDate.parse(buttonText, formato) ){
                    fechaF.value
            } else {
                   buttonText
                   },
            fontSize = 20.sp, color = green)
    }
    return buttonText
}

@Composable
fun showTimePicker(): String {
    val mContext = LocalContext.current

    val actualHour = LocalTime.now()


    val calendar = Calendar.getInstance()
    val hour = calendar[Calendar.HOUR_OF_DAY]
    val minute = calendar[Calendar.MINUTE]

    val timeSuffix = if (actualHour.hour < 12) "AM" else "PM"
    val formattedHour = if (actualHour.hour > 12) actualHour.hour - 12 else actualHour.hour
    val formattedMinute = if (actualHour.minute < 10) "0${actualHour.minute}" else actualHour.minute
    val initialTime = "$formattedHour:$formattedMinute $timeSuffix"

    val time = remember { mutableStateOf(initialTime) }
    
    val timePickerDialog = TimePickerDialog(
        mContext,
        {_, hour : Int, minute : Int ->
            val timeSuffix = if (hour < 12) "AM" else "PM"
            val formattedHour = if (hour > 12) hour - 12 else hour
            val formattedMinute = if (minute < 10) "0$minute" else minute
            time.value = "$formattedHour:$formattedMinute $timeSuffix"
            }, hour, minute, false
    )

    TextButton(onClick = { timePickerDialog.show() }) {
        Text(text = time.value, fontSize = 20.sp, color = green)
    }
    return time.toString()
}

@Composable
fun FabCheck(navController : NavController, scope : CoroutineScope, scaffoldState: ScaffoldState, onClickAction: () -> Unit){
    val context = LocalContext.current
    FloatingActionButton(onClick = {
        onClickAction()
    },
        containerColor = green,
        modifier = Modifier
            .size(80.dp)
            .padding(10.dp),
        shape = CircleShape
    ) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = null,
            modifier = Modifier.size(40.dp),
            tint = Color.White)
    }
}
