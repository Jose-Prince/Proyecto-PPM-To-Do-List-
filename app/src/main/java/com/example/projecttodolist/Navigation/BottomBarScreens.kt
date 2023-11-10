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
        icon = R.drawable.task_alt_fill0_wght400_grad0_opsz24
    )
    object Settings: BottomBarScreens(
        route = "settings",
        title = "Settings",
        icon = R.drawable.settings_fill0_wght400_grad0_opsz24
    )
    object Calendar: BottomBarScreens(
        route = "calendar",
        title = "Calendar",
        icon = R.drawable.baseline_calendar_today_24
    )

    object DailyCalendar: BottomBarScreens(
        route = "dailyCalendar",
        title = "DailyCalendar",
        icon = R.drawable.baseline_calendar_today_24
    )
}
