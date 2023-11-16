package com.example.projecttodolist.Database

import android.util.Log
import com.example.projecttodolist.GlobalVariables
import com.example.projecttodolist.GlobalVariables.tareas
import com.example.projecttodolist.GlobalVariables.userdat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class homework(
    var idtarea: String? = null,
    var title: String? = null,
    var description: String? = null,
    var type: String? = null,
    var status: String? = null,
    var timecreated: String? = null,
    var timefinished: String? = null, //ARRAY sera ahora para agregar grupos
    var grupos: String = userdat.grupos, //This is for groups
     )

fun  createtarea( idtarea: String, description: String, type: String, status:String, title: String):Boolean { //Funcion registrar datos a REALTIME
    val dbRef = FirebaseDatabase.getInstance().getReference("tareas")
    var state: Boolean
    if (title.isEmpty() ) {
        Log.e("empty data", "Los campos ingresados estan vacio")
        state = false
    }else{
        state = true
        val currentDate = LocalDate.now()
        // Define a custom date format
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        // Format the current date using the custom format
        val formattedDate = currentDate.format(formatter)

        //PARA CREAR FECHA DE CREACION ARRIBA



        tareas.idtarea = dbRef.push().key!!
        tareas.title = title
        tareas.description = description
        tareas.timecreated =formattedDate.toString()
        tareas.timefinished = "None"

        //UTILIZACION DE API JWTS
        dbRef.child(tareas.idtarea.toString()).setValue(tareas)
            .addOnCompleteListener {
                Log.e("Succesful adding", "Se ha ingresado con exito")

            }.addOnFailureListener {
                Log.e("Unsuccesful adding", "Se ha ingresado con exito")
            }


    }

    return state

}

fun updatetarea(type: String, dataaupdatear : String, idtarea: String){      //type si es el titulo que quiere cambiar

    val dbRef = FirebaseDatabase.getInstance().getReference("tareas")

    if (type == "titulo"){ // Titulo cambiado
        tareas.title = type
        dbRef.child(tareas.idtarea.toString()).setValue(tareas)

    }
    if (type == "descripcion"){ //Descipcion se puede cambiar
        tareas.description = type
        dbRef.child(tareas.idtarea.toString()).setValue(tareas)

    }
    if (type == "TimeFinished"){ //Tiempo terminado
        tareas.timefinished = type
        dbRef.child(tareas.idtarea.toString()).setValue(tareas)

    }
    if (type == "status"){ //Esta terminado o no
        tareas.status = type
        dbRef.child(tareas.idtarea.toString()).setValue(tareas)

    }
    if (type == "type"){ //Tipo de tarea
        tareas.type = type
        dbRef.child(tareas.idtarea.toString()).setValue(tareas)

    }

    //PS En el usermodel se tiene que cambiar la clase de grupo.
}

fun deletetarea(targetvalue : String, idtarea: String){ //ID de la tarea

    val dbRef = FirebaseDatabase.getInstance().getReference("usuario").child(userdat.userId.toString())


    var arraytodo = userdat.arraytodo.toString().replace(",$targetvalue", "")
    setvalue(arraytodo, "arraytodo", userdat.userId.toString())


    val dbReftarea = FirebaseDatabase.getInstance().getReference("tareas").child(idtarea)
    val mTask = dbReftarea.removeValue()
    mTask.addOnSuccessListener {
        Log.e("DELETED USER", "Se ha eliminado la tarea")

    }.addOnFailureListener{
        Log.e("USER STILL EXIST", "No se ha eliminado la tarea")
    }
}
