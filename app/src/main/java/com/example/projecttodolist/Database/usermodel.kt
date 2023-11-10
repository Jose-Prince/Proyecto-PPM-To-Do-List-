package com.example.projecttodolist.Database

import android.widget.Toast
import com.google.firebase.database.DatabaseReference


data class UserModel(
    var userId: String? = null,
    var username: String? = null,
    var password: String? = null,
    var email: String? = null,
    var createduser: String? = null,
    var arraytodo: String? = null,
    var settings: String?= null,
    var token: String?= null )

