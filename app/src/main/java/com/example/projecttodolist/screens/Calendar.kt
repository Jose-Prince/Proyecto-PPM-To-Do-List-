package com.example.projecttodolist.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projecttodolist.Functions.DrawShape
import com.example.projecttodolist.Functions.Euclides
import com.example.projecttodolist.GlobalVariables
import com.example.projecttodolist.Month
import com.example.projecttodolist.ui.theme.gray
import com.example.projecttodolist.ui.theme.green

@Composable
fun Calendar() {
    val meses = listOf<String>("Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre")
    var i by remember { mutableIntStateOf(0) }
    var mes by remember { mutableStateOf(Month(meses[(GlobalVariables.month + i)%12],GlobalVariables.year + i))}
    Box (modifier = Modifier
        .fillMaxSize()
        .background(gray)){
        Column {
            DrawShape(shape = RectangleShape)
            DrawShape(shape = RectangleShape)
        }
    }
    Column (modifier = Modifier.fillMaxWidth()){
        Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween){
            IconButton(onClick = { i--
                GlobalVariables.month--
                if (Euclides(GlobalVariables.month % 12) == 11) {
                    GlobalVariables.year--
                }
                mes = Month(meses[Euclides(GlobalVariables.month % 12)],GlobalVariables.year)
            },
                modifier = Modifier.size(45.dp)) {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = gray,
                    modifier = Modifier.size(45.dp))
            }
            Text(text = "${meses[Euclides(GlobalVariables.month % 12)]}\n${GlobalVariables.year}",
                color = gray,
                fontSize = 40.sp,
                textAlign = TextAlign.Center)
            IconButton(onClick = { i++
                GlobalVariables.month++
                if ((GlobalVariables.month)%12 == 0) {
                    GlobalVariables.year++
                }
                mes = Month(meses[Euclides(GlobalVariables.month % 12)],GlobalVariables.year)
            },
                modifier = Modifier.size(45.dp)) {
                Icon(imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = gray,
                    modifier = Modifier.size(45.dp))
            }
        }

        val daysOfWeek = listOf("DO","LU","MA","MI","JU","VI","SÁ")
        val daysOfMonth = (1..37).toList()
        Column (modifier = Modifier
            .fillMaxSize()){
            LazyVerticalGrid(columns = GridCells.Fixed(7)){
                items(daysOfWeek) { item ->
                    Text(text = item, fontSize = 27.sp,
                        textAlign = TextAlign.Center,
                        style = TextStyle(color = Color.Gray) )
                }
            }
            LazyVerticalGrid(columns = GridCells.Fixed(7)) {
                items(daysOfMonth) { item ->
                    if (item <= daysOfWeek.indexOf(mes.initialDay) || (item - daysOfWeek.indexOf(mes.initialDay)) > mes.numOfDays) {
                        Text(text = "")
                    } else {
                        Button(onClick = { /*TODO*/ },
                            modifier = Modifier
                                .background(gray)
                                .height(70.dp),
                            shape = RectangleShape,
                            colors =
                            if (GlobalVariables.MapTaskDates["${item - daysOfWeek.indexOf(mes.initialDay)}/${mes.monthNumber}/${mes.year}"] != null) {
                                ButtonDefaults.buttonColors(green)
                            } else {
                                ButtonDefaults.buttonColors(gray)
                            }
                        ) {
                            Text(text = "${item - daysOfWeek.indexOf(mes.initialDay)}",
                                fontSize = 17.sp)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CalendarPrev() {
    Calendar()
}