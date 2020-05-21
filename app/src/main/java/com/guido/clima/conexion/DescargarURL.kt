package com.guido.clima.conexion

import android.os.AsyncTask
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class DescargarURL(var completadoListener: CompletadoListener?):AsyncTask<String, Void, String>() {
    override fun doInBackground(vararg params: String): String? {
        try {
            return descargarDatos(params[0])
        } catch (e: IOException) {
            return null
        }
    }

    override fun onPostExecute(result: String) {
        try {
            completadoListener?.descargaCompleta(result)
        } catch (e:Exception){
            //catch error
        }
    }

    @Throws(IOException::class) // Por si se corta la conexión en algún momento
    private fun descargarDatos(url: String): String {
        var inputStream: InputStream? = null

        try {
            val conn = URL(url).openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            conn.connect()

            inputStream = conn.inputStream
            return inputStream.bufferedReader().use {
                it.readText()
            }
        } finally {
            inputStream?.close()
        }
    }
}