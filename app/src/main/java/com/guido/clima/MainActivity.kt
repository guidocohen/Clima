package com.guido.clima

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var tvCiudad: TextView
    private lateinit var tvGrados: TextView
    private lateinit var tvEstado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val context: Context = this

        val btConect = findViewById<Button>(R.id.btConect)
        val btRequest = findViewById<Button>(R.id.btRequest)

        btConect.setOnClickListener {
            if (Network.hayRed(context)) {
                Toast.makeText(this, "Hay red", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(
                    this,
                    "Asegúrate que haya una conexión a Internet",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        btRequest.setOnClickListener {
            if (Network.hayRed(context)) {
                Toast.makeText(this, "Hay red", Toast.LENGTH_SHORT).show()
                Log.d(
                    "HTTPRequest",
                    descargarDatos("https://stackoverflow.com/questions/32547006/connectivitymanager-getnetworkinfoint-deprecated/54641263")
                )
            } else {
                Toast.makeText(
                    this,
                    "Asegúrate que haya una conexión a Internet",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


        tvCiudad = findViewById(R.id.tvCiudad)
        tvGrados = findViewById(R.id.tvGrados)
        tvEstado = findViewById(R.id.tvEstado)

        val ciudad = intent.getStringExtra("com.guido.clima.ciudades.CIUDAD")
        val bsAs = Ciudad("Buenos Aires", 25, "Soleado")
        val saoPablo = Ciudad("Sao Pablo", 30, "Cielo despejado")

        if (ciudad == "buenos-aires") {
            tvCiudad.text = bsAs.nombre
            tvGrados.text = bsAs.grados.toString() + "°"
            tvEstado.text = bsAs.estado
        } else if (ciudad == "sao-pablo") {
            tvCiudad.text = saoPablo.nombre
            tvGrados.text = saoPablo.grados.toString() + "°"
            tvEstado.text = saoPablo.estado
        } else {
            Toast.makeText(this, "No se encuentra la información", Toast.LENGTH_SHORT).show()
        }

    }

    @Throws(IOException::class) // Por si se corta la conexión en algún momento
    private fun descargarDatos(url: String): String {

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        var inputStream: InputStream? = null
        try {
            val url = URL(url)
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            conn.connect()

            inputStream = conn.inputStream
            return inputStream.bufferedReader().use {
                it.readText()
            }
        } finally {
            if (inputStream != null) {
                inputStream.close()
            }
        }
    }
}
