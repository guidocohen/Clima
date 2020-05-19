package com.guido.clima

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class CiudadesActivity : AppCompatActivity() {
    val TAG = "com.guido.clima.ciudades.CIUDAD"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciudades)

        val bBsAs = findViewById<Button>(R.id.bArgentina)
        val bSaoPablo = findViewById<Button>(R.id.bBrasil)

        bBsAs.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(TAG, "3435910")
            startActivity(intent)
        }

        bSaoPablo.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(TAG, "3469058")
            startActivity(intent)
        }
    }
}
