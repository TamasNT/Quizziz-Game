package com.example.viralfuture

import android.content.Intent
import android.graphics.Typeface
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.CalendarContract
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var EditableText : EditText
    private var entrarmarvel = 0
    private var entrardc = 0
    private var nombre : String = ""
    private var nombreenviado  = false
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var volumen : ImageView
    private var isReleased = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EditableText = findViewById(R.id.nombre)
        volumen = findViewById(R.id.volumen)

        mediaPlayer = MediaPlayer.create(this, R.raw.avengerssong)
        mediaPlayer.start()


        BaseDatos.Puntuacion = 0
        BaseDatos.Correctas = 0
        BaseDatos.Incorrectas = 0
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer.stop()
        mediaPlayer.release()
        isReleased = true
    }

    override fun onResume() {
        super.onResume()
        if (isReleased) {
            mediaPlayer = MediaPlayer.create(this, R.raw.avengerssong)
            mediaPlayer.start()
            isReleased = false
        }
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun GoTo (view: View){
        if(nombreenviado) {
            if (entrardc == 1) {
                entrardc = 0
                entrarmarvel = 0
                BaseDatos.vista = 1
                val i = Intent(applicationContext, novenapregunta::class.java)
                startActivity(i)
            } else if (entrarmarvel == 1) {
                entrarmarvel = 0
                entrardc = 0
                BaseDatos.vista = 2
                val i = Intent(applicationContext, primerapregunta::class.java)
                startActivity(i)
            } else {
                Toast.makeText(applicationContext, "Debe elegir un tema primero", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(applicationContext, "Valide su apodo", Toast.LENGTH_SHORT).show()
        }

    }

    fun GoToPrimeraPreguntaMarvel(view: View) {
        entrarmarvel=1
        Toast.makeText(applicationContext,"Has elegido Marvel", Toast.LENGTH_SHORT).show()
    }

    fun GoToPrimeraPreguntaDC(view: View) {
        entrardc=1
        Toast.makeText(applicationContext,"Has elegido DC", Toast.LENGTH_SHORT).show()
    }

    fun EnviarNombre(view: View) {
        if(findViewById<EditText>(R.id.nombre).text.trim().length > 0){
            nombre = EditableText.text.toString()
            BaseDatos.apodo = EditableText.text.toString()
            nombreenviado=true
            Toast.makeText(applicationContext,"Apodo Validado", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(applicationContext,"Introduzca su apodo", Toast.LENGTH_SHORT).show()
        }
    }

    fun musica(view: View){
        if (mediaPlayer.isPlaying == true) {
            mediaPlayer.stop()
            mediaPlayer.prepare()
            mediaPlayer.seekTo(0)
            volumen.setImageResource(R.drawable.volumenoff)
        } else {
            mediaPlayer.start()
            volumen.setImageResource(R.drawable.volumenon)
        }
    }
}