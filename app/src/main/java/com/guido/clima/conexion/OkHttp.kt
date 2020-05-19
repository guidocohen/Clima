package com.guido.clima.conexion

import android.util.Log
import com.guido.clima.HttpActivity
import okhttp3.Call
import okhttp3.OkHttpClient
import java.io.IOException

class OkHttp(private val activity: HttpActivity) {
    // Método para OkHTTP: Mayor control en el Response
    fun request(url: String){
        val cliente = OkHttpClient()
        val solicitud = okhttp3.Request.Builder().url(url).build()

        cliente.newCall(solicitud).enqueue(object: okhttp3.Callback {
            override fun onFailure(call: Call, e: IOException) {
                //Implementar error
            }
            override fun onResponse(call: Call, response: okhttp3.Response) {
                val resultado = response.body?.string()

                activity.runOnUiThread {
                    if (resultado != null) Log.d("solicitudOkHttp", resultado)
                    else Log.d("solicitudOkHttp", "Resultado nulo")
                }
            }
        })
    }
}