package com.example.viralfuture

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class quintapregunta : AppCompatActivity() {
    private lateinit var countDownTimer: CountDownTimer
    private var timeLeftInMilliseconds = 50 * 1000L
    private lateinit var timer: TextView
    private lateinit var EditableText : EditText
    private var nombre : String = ""
    private var nombreenviado  = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quintapregunta)

        EditableText = findViewById(R.id.respuesta)
        val timer: TextView = findViewById<TextView>(R.id.timer)


        // Contador que mostramos en Pantalla para Saltar de Vista
        countDownTimer = object : CountDownTimer(timeLeftInMilliseconds, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMilliseconds = millisUntilFinished
                val timeLeft = millisUntilFinished / 1000
                timer.text = String.format("%02d : %02d", timeLeft / 60, timeLeft % 60)
            }

            override fun onFinish() {
                if(nombre.lowercase() == "asgard") {
                    BaseDatos.Correctas++
                    BaseDatos.Puntuacion++
                }
                else{
                    BaseDatos.Incorrectas++
                }
                val i = Intent(applicationContext, sextrapregunta::class.java)
                startActivity(i)
            }
        }
        countDownTimer.start()
    }

    // Metodo para que se detenga el Contador al saltar de vista
    override fun onPause() {
        super.onPause()
        countDownTimer.cancel()
    }

    // Metodo para volver a una vista especifica
    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    // Metodo para Cambiar imagen al presionar
    fun pista(view: View) {
        val imagen = findViewById<ImageView>(R.id.pista)

        if(view.id == R.id.pista){
            imagen.setImageResource(R.drawable.pistatextcheck)
            Toast.makeText(applicationContext,"Posee un palacio dorado llamado Bilskirnir", Toast.LENGTH_SHORT).show()
        }
    }

    // Metodo para cambiar de vista al presionar el boton
    fun GoToSextaPregunta (view: View){
        if(nombreenviado){
            if(nombre.lowercase()=="asgard") {
                BaseDatos.Correctas++
                BaseDatos.Puntuacion++
            }
            else{
                BaseDatos.Incorrectas++
            }
            val i = Intent(applicationContext, sextrapregunta::class.java)
            startActivity(i)
        }else{
            Toast.makeText(applicationContext,"Valide su respuesta", Toast.LENGTH_SHORT).show()
        }
    }

    // Metodo para enviar los datos del EditText
    fun EnviarRespuesta(view: View) {
        if(findViewById<EditText>(R.id.respuesta).text.trim().length > 0){
            nombre = EditableText.text.toString()
            nombreenviado=true
            Toast.makeText(applicationContext,"Respuesta Validada", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(applicationContext,"Introduzca su respuesta", Toast.LENGTH_SHORT).show()
        }
    }

}