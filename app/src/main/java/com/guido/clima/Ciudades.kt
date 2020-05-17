package com.guido.clima

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class Ciudades : AppCompatActivity() {
    val TAG = "com.guido.clima.ciudades.CIUDAD"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciudades)

        val bBsAs = findViewById<Button>(R.id.bArgentina)
        val bSaoPablo = findViewById<Button>(R.id.bBrasil)

        bBsAs.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(TAG, "buenos-aires")
            startActivity(intent)
        })

        bSaoPablo.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(TAG, "sao-pablo")
            startActivity(intent)
        })
    }
}