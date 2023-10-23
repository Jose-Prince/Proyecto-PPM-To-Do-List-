package com.example.projecttodolist.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projecttodolist.Functions.DrawShape
import com.example.projecttodolist.Functions.Euclides
import com.example.projecttodolist.GlobalVariables
import com.example.projecttodolist.Month
import com.example.projecttodolist.ui.theme.gray

@Composable
fun Calendar() {
    val meses = listOf<String>("Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Novimebre","Diciembre")
    var i by remember { mutableIntStateOf(0) }
    var e by remember { mutableIntStateOf(1) }
    var mes by remember { mutableStateOf(Month(meses[(GlobalVariables.month + i)%12],GlobalVariables.year + i,i))}
    Box (modifier = Modifier
        .fillMaxSize()
        .background(gray)){
        DrawShape(shape = RectangleShape)
    }
    Column {
        Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){
            IconButton(onClick = { i--
                                    GlobalVariables.month--
                                    if (Euclides(GlobalVariables.month % 12) == 11) {
                                        GlobalVariables.year--
                                    }
                                    mes = Month(meses[Euclides(GlobalVariables.month % 12)],GlobalVariables.year,i)
            }) {
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = gray)
            }
            Text(text = "${meses[Euclides(GlobalVariables.month % 12)]}${GlobalVariables.year}",
                color = gray)
            IconButton(onClick = { i++
                                    GlobalVariables.month++
                                    if ((GlobalVariables.month)%12 == 0) {
                                        GlobalVariables.year++
                                    }
                                    mes = Month(meses[Euclides(GlobalVariables.month % 12)],GlobalVariables.year,i)
            }) {
                Icon(imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    tint = gray)
            }
        }

        val daysOfWeek = listOf("DO","LU","MA","MI","JU","VI","SÁ")
        val daysOfMonth = (1..37).toList()
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp)){
            LazyVerticalGrid(columns = GridCells.Fixed(7)){
                items(daysOfWeek) { item ->
                    Text(text = item, fontSize = 20.sp)
                }
            }
            LazyVerticalGrid(columns = GridCells.Fixed(7)) {
                items(daysOfMonth) { item ->
                    if (item <= daysOfWeek.indexOf(mes.initialDay)) {
                        Text(text = "")
                    } else {
                        Text(text = if (item - daysOfWeek.indexOf(mes.initialDay) > mes.numOfDays) {
                            ""
                        } else {
                            "${item - daysOfWeek.indexOf(mes.initialDay)}"
                        })
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