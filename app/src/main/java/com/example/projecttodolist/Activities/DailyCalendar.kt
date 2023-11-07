package com.example.projecttodolist.Activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.projecttodolist.Functions.DrawShape
import com.example.projecttodolist.GlobalVariables
import com.example.projecttodolist.Tarea
import com.example.projecttodolist.screens.MainTaskScreen
import com.example.projecttodolist.ui.theme.gray
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import com.example.projecttodolist.Functions.*
import com.example.projecttodolist.screens.Calendar
import com.example.projecttodolist.ui.theme.blue
import com.example.projecttodolist.ui.theme.green

class DailyCalendar() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var date : String?= ""
            val navController = rememberNavController()
            val extras = intent.extras
            if (extras != null) {
                date = extras.getString("fecha")
            }
            DailyCalendar(date, GlobalVariables.MapTaskHours)
        }
    }
}
@Composable
fun DailyCalendar(date: String?, mapTaskHours: Map<String, Map<String, ArrayList<Tarea>>>) {
    val context = LocalContext.current

    Box (modifier = Modifier
        .fillMaxSize()
        .background(gray)){
        DrawShape(shape = RectangleShape)
    }
    Column(modifier = Modifier.fillMaxSize()){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
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
            if (date != null) {
                Text(
                    text = date,
                    color = Color.Gray
                )
            }
        }

        LazyColumn (modifier = Modifier.fillMaxSize()){
            items(24) { hora ->
                val horaStr = "%02d:00".format(hora)

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "$horaStr   ────────────────────────────────", modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth())
                    organizeHoursInMap(GlobalVariables.listWithAllDates,GlobalVariables.MapTaskHours)
                    val hour = convertTo12HourFormat(horaStr)
                    mapTaskHours[date]?.get(hour)?.let { elementos ->
                        elementos.forEach { elemento ->
                            ElementoHora(elemento)
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun ElementoHora(task: Tarea) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(3.dp, blue, RectangleShape)
            .background(gray),
        backgroundColor = gray
    ) {
        Text(
            text = task.name,
            modifier = Modifier
                .padding(5.dp),
            fontSize = 20.sp
        )
    }
}

@Preview
@Composable
fun DailyPrev() {
    val navController = rememberNavController()
    DailyCalendar("06/11/2023", GlobalVariables.MapTaskHours)
}