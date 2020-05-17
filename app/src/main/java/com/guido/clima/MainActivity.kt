package com.guido.clima

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var tvCiudad:TextView
    private lateinit var tvGrados:TextView
    private lateinit var tvEstado:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCiudad = findViewById(R.id.tvCiudad)
        tvGrados = findViewById(R.id.tvGrados)
        tvEstado = findViewById(R.id.tvEstado)

        val ciudad = intent.getStringExtra("com.guido.clima.ciudades.CIUDAD")
        val bsAs = Ciudad("Buenos Aires", 25, "Soleado")
        val saoPablo = Ciudad("Sao Pablo", 30, "Cielo despejado")

        if(ciudad == "buenos-aires"){
            tvCiudad.text = bsAs.nombre
            tvGrados.text = bsAs.grados.toString() + "°"
            tvEstado.text = bsAs.estado
        } else if(ciudad == "sao-pablo"){
            tvCiudad.text = saoPablo.nombre
            tvGrados.text = saoPablo.grados.toString() + "°"
            tvEstado.text = saoPablo.estado
        }else {
            Toast.makeText(this,"No se encuentra la información",Toast.LENGTH_SHORT).show()
        }

    }
}
