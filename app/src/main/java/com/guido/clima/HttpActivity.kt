package com.guido.clima

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.guido.clima.conexion.*

class HttpActivity : AppCompatActivity(),
    CompletadoListener {

    private val volley by lazy { VolleyHttp(this) }
    private val okHttp by lazy { OkHttp(this@HttpActivity) }

    override fun descargaCompleta(resultado:String){
        Log.d("descargaCompleta", resultado)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_http)

        val bConnect = findViewById<Button>(R.id.bConnect)
        val bRequest = findViewById<Button>(R.id.bRequest)
        val bVolley = findViewById<Button>(R.id.bVolley)
        val bOk = findViewById<Button>(R.id.bOk)

        bConnect.setOnClickListener {
            if (Network.hayRed(this)) {
                Toast.makeText(this, "Hay red", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    this,
                    "Asegúrate que haya una conexión a Internet",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Request Nativo
        bRequest.setOnClickListener {
            if (Network.hayRed(this)) {
                DescargarURL(this).execute("https://stackoverflow.com/questions/32547006/connectivitymanager-getnetworkinfoint-deprecated/54641263")
                Toast.makeText(this, "Se descargó la URL de forma Nativa", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    this,
                    "Asegúrate que haya una conexión a Internet",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Request Volley de Google
        bVolley.setOnClickListener {
            if (Network.hayRed(this)) {
                volley.request("https://stackoverflow.com/questions/32547006/connectivitymanager-getnetworkinfoint-deprecated/54641263")
                Toast.makeText(this, "Se descargó la URL con Volley", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    this,
                    "Asegúrate que haya una conexión a Internet",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // Request con OkHttp
        bOk.setOnClickListener {
            if (Network.hayRed(this)) {
                okHttp.request("https://stackoverflow.com/questions/32547006/connectivitymanager-getnetworkinfoint-deprecated/54641263")
                Toast.makeText(this, "Se descargó la URL con OkHttp", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    this,
                    "Asegúrate que haya una conexión a Internet",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
