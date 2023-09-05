package com.example.projecttodolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


class EditActivity : ComponentActivity() {

    fun OnCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContent {
            Text(text = "Hola")
        }
    }
}

@Preview
@Composable
fun TextoPrev() {
    Text(text = "Hola")
}

