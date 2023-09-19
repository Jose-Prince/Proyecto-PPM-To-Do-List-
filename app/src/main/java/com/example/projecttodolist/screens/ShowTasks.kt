package com.example.projecttodolist.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.projecttodolist.GlobalVariables
import com.example.projecttodolist.ui.theme.blue
import com.example.projecttodolist.ui.theme.darkblue
import com.example.projecttodolist.ui.theme.gray
import com.example.projecttodolist.ui.theme.green

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Show(navController: NavController) {
    val customShape = Path().apply {
        lineTo(0f,32f)
        lineTo(64f,0f)
        lineTo(64f,64f)
        close()
    }

    var listTask = GlobalVariables.listOfTasks
    Column (modifier = Modifier
        .fillMaxSize()
        .background(gray)){

        for (task in listTask){
            Row {
                Spacer(modifier = Modifier.width(18.dp))
                Button(onClick = { /*TODO*/ },
                    modifier = Modifier
                        .width(300.dp)
                        .border(
                            width = 0.dp,
                            Color.Transparent,
                            RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp)
                        )
                        .background(blue, shape = RoundedCornerShape(16.dp)),
                    colors = ButtonDefaults.buttonColors(green),
                    shape = RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp)
                ) {
                    Text(text = task.name,
                        fontSize = 24.sp)
                }
                Divider(
                    modifier = Modifier
                        .width(3.dp)
                        .height(48.dp),
                    color = Color.Black
                )
                IconButton(onClick = { /*TODO*/ },
                    modifier = Modifier
                        .background(
                            green,
                            shape = RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp)
                        )
                        .border(
                            width = 0.dp,
                            Color.Transparent,
                            RoundedCornerShape(topEnd = 16.dp, bottomEnd = 16.dp)
                        )) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = null,
                        modifier = Modifier.size(110.dp),
                        tint = darkblue)
                }

                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }
}

@Preview
@Composable
fun ShowPrev() {
    val navController = rememberNavController()
    Show(navController = navController)
}


