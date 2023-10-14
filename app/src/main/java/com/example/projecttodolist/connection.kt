package com.example.projecttodolist

import android.content.Context
import android.util.Log

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
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


fun loginrequest(context: Context):String {
    var dataconnection = dataconnection()
    val queue = Volley.newRequestQueue(context)
    val url = "http://127.0.0.1/login/login.php"
    var response = ""

    val stringRequest = StringRequest(Request.Method.GET,
        url, {
            // Handling Success
            Log.d("Success", "simpleRequest:${it}")
            response = it

        }, {
            // Handling Error
            Log.d("Error", "simpleRequest:${it}")
            response = "Error"
        })
    queue.add(stringRequest)
    return response
}
