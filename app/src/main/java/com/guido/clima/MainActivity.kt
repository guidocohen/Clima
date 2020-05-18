package com.guido.clima

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.lang.Exception

class MainActivity : AppCompatActivity(), CompletadoListener {
    private lateinit var tvCiudad: TextView
    private lateinit var tvGrados: TextView
    private lateinit var tvEstado: TextView

    override fun descargaCompleta(resultado:String){
        Log.d("descargaCompleta", resultado)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val context: Context = this

        val bConnect = findViewById<Button>(R.id.bConnect)
        val bRequest = findViewById<Button>(R.id.bRequest)
        val bVolley = findViewById<Button>(R.id.bVolley)


        bConnect.setOnClickListener {
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

        bRequest.setOnClickListener {
            if (Network.hayRed(context)) {
                Toast.makeText(this, "Hay red", Toast.LENGTH_SHORT).show()
                DescargarURL(this).execute("https://stackoverflow.com/questions/32547006/connectivitymanager-getnetworkinfoint-deprecated/54641263")
            } else {
                Toast.makeText(
                    this,
                    "Asegúrate que haya una conexión a Internet",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        bVolley.setOnClickListener {
            if (Network.hayRed(context)) {
                solicitudHTTPVolley("https://stackoverflow.com/questions/32547006/connectivitymanager-getnetworkinfoint-deprecated/54641263")
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
            tvGrados.text = bsAs.grados.toString().plus("°")
            tvEstado.text = bsAs.estado
        } else if (ciudad == "sao-pablo") {
            tvCiudad.text = saoPablo.nombre
            tvGrados.text = saoPablo.grados.toString().plus("°")
            tvEstado.text = saoPablo.estado
        } else {
            Toast.makeText(this, "No se encuentra la información", Toast.LENGTH_SHORT).show()
        }

    }

    //Metodo para Volley
    private fun solicitudHTTPVolley(url:String) {
        val queue = Volley.newRequestQueue(this)
        val solicitud = StringRequest(Request.Method.GET, url, Response.Listener<String>{
            Log.d("solicitudHTTPVolleyy", it)
        }, Response.ErrorListener {  })

        queue.add(solicitud)
    }

}
