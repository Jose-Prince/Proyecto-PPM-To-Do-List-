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

    object AddContact: BottomBarScreens(
        route = "addRequest",
        title = "Add Contact",
        icon = R.drawable.baseline_person_add_alt_1_24
    )

    object ShowCollabs: BottomBarScreens(
        route = "showCollabs",
        title = "Show Collabs",
        icon = R.drawable.baseline_person_24
    )

    object ShowGroups: BottomBarScreens(
        route = "showGroups",
        title = "Show Groups",
        icon = R.drawable.baseline_people_alt_24
    )
}
