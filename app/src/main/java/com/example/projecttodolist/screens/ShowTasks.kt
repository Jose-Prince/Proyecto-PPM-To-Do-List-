package com.example.projecttodolist.screens

import android.annotation.SuppressLint
import android.widget.Space
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissState
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.projecttodolist.Activities.DrawShape
import com.example.projecttodolist.GlobalVariables
import com.example.projecttodolist.NameAdjust
import com.example.projecttodolist.Tarea
import com.example.projecttodolist.TaskViewModel
import com.example.projecttodolist.ui.theme.blue
import com.example.projecttodolist.ui.theme.darkblue
import com.example.projecttodolist.ui.theme.gray
import com.example.projecttodolist.ui.theme.green
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.jar.Attributes.Name

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Show(taskViewModel : TaskViewModel = viewModel()) {

    var listTask = GlobalVariables.listOfTasks
    var listDates = GlobalVariables.listOfDates
    val formato = SimpleDateFormat("dd/MM/yyyy")
    val state = rememberScrollState()

    val tasks by taskViewModel.taskState.collectAsState()

    MaterialTheme {
        androidx.compose.material.Scaffold (backgroundColor = gray){
            paddingValues ->
            LazyColumn(modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
                contentPadding = PaddingValues(vertical = 12.dp),

                ){
                itemsIndexed(
                    items = tasks,
                    key = { _, item -> item.hashCode() }
                ) { index, taskContent ->
                    TaskItem(taskContent,
                        onRemove = taskViewModel::removeItem)
                    if (index < tasks.size - 1) {
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ShowPrev() {
    val navController = rememberNavController()
    Show()
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DismissBackground(dismissState : DismissState) {
    val color = when (dismissState.dismissDirection) {
        DismissDirection.StartToEnd -> Color(0xFFFF1744)
        DismissDirection.EndToStart -> Color(0xFF1DE986)
        null -> Color.Transparent
    }
    val direction = dismissState.dismissDirection

    Row (
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(12.dp, 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        if (direction == DismissDirection.StartToEnd) Icon(
            Icons.Default.Close,
            contentDescription = "delete"
        )
        Spacer(modifier = Modifier)
        if (direction == DismissDirection.EndToStart) androidx.compose.material3.Icon(
            imageVector = Icons.Default.Check,
            contentDescription = "Done"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskCard(task : Tarea) {
    androidx.compose.material3.ListItem(
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)
            .border(3.dp, green),
        headlineText = { Text(
            text = NameAdjust(task.name),
            style = MaterialTheme.typography.titleMedium)},
        colors = ListItemDefaults.colors(gray),
        supportingText = {
            Text(text = task.date,
                style = MaterialTheme.typography.bodySmall)
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TaskItem(task: Tarea, onRemove: (Tarea) -> Unit) {
    val context = LocalContext.current
    var show by remember { mutableStateOf(true) }
    val currentItem by rememberUpdatedState(task)
    val dismissState = rememberDismissState(
        confirmStateChange = {
            if (it == DismissValue.DismissedToStart || it == DismissValue.DismissedToEnd) {
                show = false
                true
            } else false
        }
    )
    AnimatedVisibility(show, exit = fadeOut(spring())){
        SwipeToDismiss(
            state = dismissState,
            background = { DismissBackground(dismissState = dismissState)},
            dismissContent = {
                TaskCard(task)
            }
        )
    }

    LaunchedEffect(show) {
        if (!show) {
            delay(800)
            onRemove(currentItem)
            Toast.makeText(context, "Item removed", Toast.LENGTH_SHORT).show()
        }
    }
}


