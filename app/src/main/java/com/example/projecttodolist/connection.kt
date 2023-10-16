package com.example.projecttodolist

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class dataconnection(){
    private var url :String= "http://127.0.0.1/login/login.php"
    private var Username: String = "";
    private var Email: String = ""
    private  var Password : String = ""
    private var Age : Int = 0
    private  var Arraytodo = arrayListOf<String>()
    private var settings  = arrayListOf<String>()

}


@Composable //Agregue el composable
fun loginrequest() {
    val urllink = "http://10.0.2.2:8085/datatodo/mostrardatos.php" //Utilizado SOLAMENTE CON EMULADOR
    val queue: RequestQueue = Volley.newRequestQueue(LocalContext.current) //En caso de emergencia el cambio fue de LocalContext.current


    val request = StringRequest(
        Request.Method.GET,urllink,
        { response ->

            val data = JSONArray(response).toString()

            Log.e("Objects",data)


        },
        { Log.e("Objects","sin conex") })
    queue.add(request)
}