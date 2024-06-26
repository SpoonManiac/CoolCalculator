package com.fiat.coolcalculator

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat

class MediaFinalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mediafinalactivity)

        val PAI: EditText = findViewById(R.id.pai)
        val MAP: EditText = findViewById(R.id.map)
        val PROVA: EditText = findViewById(R.id.prova)
        val saida: EditText = findViewById(R.id.resposta)
        val calcular: Button = findViewById(R.id.calcular)
        val reset: Button = findViewById(R.id.reset)

        val watcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    val str = it.toString()
                    if (str.contains(",")) {
                        PAI.removeTextChangedListener(this)
                        MAP.removeTextChangedListener(this)
                        PROVA.removeTextChangedListener(this)

                        PAI.setText(str.replace(",", "."))
                        MAP.setText(str.replace(",", "."))
                        PROVA.setText(str.replace(",", "."))

                        PAI.setSelection(PAI.text.length)
                        MAP.setSelection(MAP.text.length)
                        PROVA.setSelection(PROVA.text.length)

                        PAI.addTextChangedListener(this)
                        MAP.addTextChangedListener(this)
                        PROVA.addTextChangedListener(this)
                    }
                }
            }
        }

        PAI.addTextChangedListener(watcher)
        MAP.addTextChangedListener(watcher)
        PROVA.addTextChangedListener(watcher)

        calcular.setOnClickListener {
            val stringPAI = PAI.text.toString()
            val stringMAP = MAP.text.toString()
            val stringPROVA = PROVA.text.toString()
            val numeroPAI = stringPAI.toDoubleOrNull()
            val numeroMAP = stringMAP.toDoubleOrNull()
            val numeroPROVA = stringPROVA.toDoubleOrNull()

            if (numeroPAI != null && numeroMAP != null && numeroPROVA != null) {
                val resultado = (numeroMAP * 0.3) + (numeroPROVA * 0.4) + (numeroPAI * 0.3)
                val formattedResult = String.format("%.2f", resultado)
                saida.setText(formattedResult)
            } else {
                saida.setText("Entrada inválida")
            }
        }

        reset.setOnClickListener {
            PAI.text.clear()
            MAP.text.clear()
            PROVA.text.clear()
            saida.text.clear()
        }
    }
}
