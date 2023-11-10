package com.example.projecttodolist.screens

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import com.example.projecttodolist.ui.theme.blue
import com.example.projecttodolist.ui.theme.gray

@Composable
fun AppBar(
    onNavigationIconClick: () -> Unit
) {
            IconButton(onClick = onNavigationIconClick) {
                Icon(imageVector = Icons.Default.Menu,
                    contentDescription = null)
            }
}