package com.example.projecttodolist

sealed class items_menu(
    val icon: Int,
    val title: String,
    val ruta: String
) {
    object PantallaActivities: items_menu(R.drawable.baseline_format_align_justify_24, "Activity", "screen1")

    object  PanatllaActivities: items_menu(R.drawable.baseline_more_vert_24, "Configuration", "screen2")
}
