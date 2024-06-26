package com.fiat.coolcalculator

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val entrada1: EditText = findViewById(R.id.numero1)
        val entrada2: EditText = findViewById(R.id.numero2)
        val saida: EditText = findViewById(R.id.valorSaida)
        val calcular: Button = findViewById(R.id.calcular)
        val reset: Button = findViewById(R.id.reset)
        val operadores: Spinner = findViewById(R.id.operadores)
        val mediaFinal: Button = findViewById(R.id.mediaFinal)



        val operacoes = arrayOf("+", "-", "x", "%")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, operacoes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        operadores.adapter = adapter


        calcular.setOnClickListener {
            val string1 = entrada1.text.toString()
            val string2 = entrada2.text.toString()
            val numero1 = string1.toDoubleOrNull()
            val numero2 = string2.toDoubleOrNull()

            if (numero1 != null && numero2 != null) {
                val selectedOperation = operadores.selectedItem.toString()
                val resultado = when (selectedOperation) {
                    "+" -> numero1 + numero2
                    "-" -> numero1 - numero2
                    "x" -> numero1 * numero2
                    "%" -> if (numero2 != 0.0) numero1 / numero2 else "Divisão por zero"
                    else -> "Operação inválida"
                }
                saida.setText(resultado.toString())


            } else {
                saida.setText("Entrada inválida")
            }
        }

        mediaFinal.setOnClickListener {
            val intent = Intent(this, MediaFinalActivity::class.java)
            startActivity(intent)
            }

        reset.setOnClickListener{
            entrada1.text.clear()
            entrada2.text.clear()
            saida.text.clear()
        }


    }
}