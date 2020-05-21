package com.guido.clima

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.appbar.MaterialToolbar
import com.google.gson.Gson
import com.guido.clima.conexion.Network

class MainActivity : AppCompatActivity() {
    private lateinit var tvCiudad: TextView
    private lateinit var tvGrados: TextView
    private lateinit var tvEstado: TextView
    private lateinit var toolbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        toolbar.setTitle(R.string.app_name)
        tvCiudad = findViewById(R.id.tvCiudad)
        tvGrados = findViewById(R.id.tvGrados)
        tvEstado = findViewById(R.id.tvEstado)

        val ciudad = intent.getStringExtra("com.guido.clima.ciudades.CIUDAD")

        if (Network.hayRed(this)) {
            Toast.makeText(this, "Hay red", Toast.LENGTH_SHORT).show()
            volleyRequest("https://api.openweathermap.org/data/2.5/weather?id=${ciudad}&appid=e018858aca5d8b2e5a52ac03401d73d9&units=metric&lang=es")

            //e018858aca5d8b2e5a52ac03401d73d9
            //3435910

        } else {
            Toast.makeText(this, "No hay red", Toast.LENGTH_SHORT).show()
        }
    }

    private fun volleyRequest(url: String) {
        val queue = Volley.newRequestQueue(this)
        val solicitud = StringRequest(Request.Method.GET, url, Response.Listener<String> {
            Log.d("solicitudHTTPVolley", it)
            val gson = Gson()
            val city = gson.fromJson(it, Ciudad::class.java)
            tvCiudad.text = city?.name
            tvGrados.text = city?.main?.temp.toString().plus("°")
            tvEstado.text = city?.weather?.get(0)?.description
        }, Response.ErrorListener {
            Log.d("Error Volley: ", it.toString())
        })

        queue.add(solicitud)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.bFav -> {
                Toast.makeText(this, "Elemento añadido como favorito", Toast.LENGTH_SHORT).show()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

}
