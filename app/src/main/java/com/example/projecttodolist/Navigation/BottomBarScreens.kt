package com.example.projecttodolist.Navigation

import android.icu.text.CaseMap.Title
import com.example.projecttodolist.R

sealed class BottomBarScreens(
    val route: String,
    val title: String,
    val icon: Int
) {
    object ShowTasks: BottomBarScreens(
        route = "showTasks",
        title = "ShowTasks",
        icon = R.drawable.baseline_format_align_justify_24
    )
    object Settings: BottomBarScreens(
        route = "settings",
        title = "Settings",
        icon = R.drawable.baseline_format_align_justify_24
    )
}
