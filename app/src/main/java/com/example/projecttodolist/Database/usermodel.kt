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

class usermodel {

    private lateinit var dbRef: DatabaseReference


    private fun saveEmployeeData() {

        if (cName.isEmpty()) {
            etchocName.error = "Please enter name"
        }
        if (cAge.isEmpty()) {
            etchocAge.error = "Please enter ExpireDate"
        }
        if (cSalary.isEmpty()) {
            etchocSalary.error = "Please enter price"
        }

        val empId = dbRef.push().key!!

        val employee = ChocolateModel(empId, cName, cAge, cSalary)

        dbRef.child(empId).setValue(employee)
            .addOnCompleteListener {
                Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()

                etchocName.text.clear()
                etchocAge.text.clear()
                etchocSalary.text.clear()


            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
            }

    }
}