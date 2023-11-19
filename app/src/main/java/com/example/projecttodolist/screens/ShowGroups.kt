package com.example.projecttodolist.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.projecttodolist.Navigation.AppScreens
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

data class Group(val id: String, val name: String)

suspend fun fetchData(onSuccess: (List<Group>) -> Unit) {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("https://api.example.com/groups")
        .build()

    val response = withContext(Dispatchers.IO) {
        client.newCall(request).execute()
    }

    if (response.isSuccessful) {
        val responseData = response.body?.string()
        val groups = parseJson(responseData)
        onSuccess(groups)
    } else {
        println("Ha ocurrido un error!")
    }
}

fun parseJson(json: String?): List<Group> {
    return emptyList()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowGroups(navController: NavController) {
    val navController = rememberNavController()
    val groups = remember { mutableStateOf<List<Group>>(emptyList()) }
    val context = LocalContext.current

//    LaunchedEffect(true) {
//        fetchData { newGroups ->
////            withContext(Dispatchers.Main) {
//                groups.value = newGroups
////            }
//        }
//    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(text = "Show Groups Screen Content")
        Spacer(modifier = Modifier.height(16.dp))
        // Display the list of groups
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            LazyColumn {
                items(groups.value) { group ->
                    Text(text = group.name)
                }
            }
        }
        Button(
            onClick = { navController.navigate(AppScreens.Bar.route) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = "Go Home")
        }
    }
}

@Preview
@Composable
fun ShowGroupsScreenPrev() {
    val navController = rememberNavController()
    ShowGroups(navController)
}