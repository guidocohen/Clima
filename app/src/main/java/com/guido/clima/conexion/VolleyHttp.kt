package com.guido.clima.conexion

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.guido.clima.Ciudad

class VolleyHttp(private val context: Context) {

    //Método para Volley: Implementación más simple y clara
    fun request(url:String) {
        val queue = Volley.newRequestQueue(context)
        val solicitud = StringRequest(Request.Method.GET, url, Response.Listener<String>{
            Log.d("solicitudHTTPVolley", it)
        }, Response.ErrorListener {
            Log.d("Error Volley: ", it.toString())
        })
        queue.add(solicitud)
    }
}