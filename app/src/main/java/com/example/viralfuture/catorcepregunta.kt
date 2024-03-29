package com.example.viralfuture

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast

class catorcepregunta : AppCompatActivity() {
    private lateinit var countDownTimer: CountDownTimer
    private var timeLeftInMilliseconds = 50 * 1000L
    private lateinit var timer: TextView
    private lateinit var Opcion1 : RadioButton
    private lateinit var Opcion2 : RadioButton
    private lateinit var Opcion3 : RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catorcepregunta)

        Opcion1 = findViewById(R.id.opcion1)
        Opcion2 = findViewById(R.id.opcion2)
        Opcion3 = findViewById(R.id.opcion3)
        val timer: TextView = findViewById<TextView>(R.id.timer)

        //Contador que mostramos en Pantalla para Saltar de Vista
        countDownTimer = object : CountDownTimer(timeLeftInMilliseconds, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeftInMilliseconds = millisUntilFinished
                val timeLeft = millisUntilFinished / 1000
                timer.text = String.format("%02d : %02d", timeLeft / 60, timeLeft % 60)
            }

            override fun onFinish() {
                if (Opcion3.isChecked) {
                    BaseDatos.Correctas++
                    BaseDatos.Puntuacion++
                }else{
                    BaseDatos.Incorrectas++

                }
                val i = Intent(applicationContext, quincepregunta::class.java)
                startActivity(i)
            }
        }
        countDownTimer.start()
    }

    // Metodo para que se detenga el Contador al saltar de vist
    override fun onPause() {
        super.onPause()
        countDownTimer.cancel()
    }

    //Metodo para volver a una vista especifica
    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    // Metodo para Cambiar imagen al presionar
    fun pista(view: View) {
        val imagen = findViewById<ImageView>(R.id.pista)

        if (view.id == R.id.pista) {
            imagen.setImageResource(R.drawable.ideacheck)
            Toast.makeText(applicationContext, "Relacionado con los murciélagos", Toast.LENGTH_SHORT)
                .show()
        }
    }

    // Metodo para cambiar de vista al presionar el boton
    fun GoToQuincePregunta(view: View) {

        //Comprobamos que la respuesta sea correcta
        if (Opcion3.isChecked){
            BaseDatos.Correctas++
            BaseDatos.Puntuacion++
        }else{
            BaseDatos.Incorrectas++
        }
        val i = Intent(applicationContext, quincepregunta::class.java)
        startActivity(i)
    }
}