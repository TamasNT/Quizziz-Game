package com.example.viralfuture

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

private lateinit var nombre: TextView
private lateinit var presentacion: TextView
private lateinit var logoGrande: ImageView
private lateinit var logopequeno: ImageView
private lateinit var logofondo: ImageView
private lateinit var puntuacion: TextView
private lateinit var correctas: TextView
private lateinit var incorrectas: TextView


class Puntaje : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puntaje)

        nombre = findViewById(R.id.felicidades)
        presentacion = findViewById(R.id.textofelicitacion)
        logoGrande = findViewById(R.id.logogrande)
        logopequeno = findViewById(R.id.logopequeno)
        logofondo = findViewById(R.id.fondo)
        puntuacion = findViewById(R.id.puntos)
        correctas = findViewById(R.id.numcorrectas)
        incorrectas = findViewById(R.id.numincorrectas)


        if (BaseDatos.Puntuacion > 4){
            nombre.setText("Felicitaciones, ${BaseDatos.apodo}").toString()
        }else {
            nombre.setText("Pudiste hacerlo mejor, ${BaseDatos.apodo}").toString()
        }
        puntuacion.setText("${BaseDatos.Puntuacion}/8").toString()
        correctas.setText("${BaseDatos.Correctas}").toString()
        incorrectas.setText("${BaseDatos.Incorrectas}").toString()

        if(BaseDatos.vista == 1){
            presentacion.setText("Héroe de DC").toString()
            logopequeno.setImageResource(R.drawable.dclogo)
            logoGrande.setImageResource(R.drawable.dclogo)
            logofondo.setImageResource(R.drawable.dclogoew)
        }


    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun CompartirDatos(view: View){
        val puntos = puntuacion.text.toString()
        val incorrectas = incorrectas.text.toString()
        val correctas = correctas.text.toString()

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            if(BaseDatos.vista == 1) {
                if(BaseDatos.Puntuacion < 4){
                    putExtra(Intent.EXTRA_TEXT, "Soy una Deshonra de DC!!!\nObtuve $puntos puntos\n$incorrectas incorrectas y $correctas correctas\nDescarga y juega tu tambien: https://www.mediafire.com/folder/n1kocg9fc3rrs/Quizziz+Hero+App ")
                }else {
                    putExtra(Intent.EXTRA_TEXT, "Soy un Héroe de DC!!!\nObtuve $puntos puntos\nSolo $incorrectas incorrectas y $correctas correctas\nDescarga y juega tu tambien: https://www.mediafire.com/folder/n1kocg9fc3rrs/Quizziz+Hero+App ")
                }
            } else {
                if(BaseDatos.Puntuacion < 4){
                    putExtra(Intent.EXTRA_TEXT, "Soy una Deshonra de Marvel!!!\nObtuve $puntos puntos\n$incorrectas incorrectas y $correctas correctas\nDescarga y juega tu tambien: https://www.mediafire.com/folder/n1kocg9fc3rrs/Quizziz+Hero+App ")
                }else {
                    putExtra(Intent.EXTRA_TEXT, "Soy un Héroe de Marvel!!!\nObtuve $puntos puntos\nSolo $incorrectas incorrectas y $correctas correctas\nDescarga y juega tu tambien: https://www.mediafire.com/folder/n1kocg9fc3rrs/Quizziz+Hero+App ")
                }
            }
            type = "text/plain"
        }
        startActivity(Intent.createChooser(sendIntent, "Compartir con"))
    }
}
