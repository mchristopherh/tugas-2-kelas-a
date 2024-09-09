package com.example.calculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val number1: EditText = findViewById(R.id.number1)
        val number2: EditText = findViewById(R.id.number2)

        val radioGroup: RadioGroup = findViewById(R.id.radiogrup)

        val btnHitung: Button = findViewById(R.id.btn_hitung)

        btnHitung.setOnClickListener {
            val input1 = number1.text.toString().toDoubleOrNull()
            val input2 = number2.text.toString().toDoubleOrNull()
            val selectedButtonId = radioGroup.checkedRadioButtonId

            if (input1 == null || input2 == null || selectedButtonId == -1) {
                return@setOnClickListener
            }

            val pilihanOperasi = findViewById<RadioButton>(selectedButtonId)
            val operator = pilihanOperasi.text.toString()
            val result = if (operator == "+") {
                input1 + input2
            } else if (operator == "-") {
                input1 - input2
            } else if (operator == "×") {
                input1 * input2
            } else if (operator == "÷" && input2 != 0.0) {
                input1 / input2
            } else {
                null
            }

            if (result != null) {
                val intent = Intent(this, MainActivity2::class.java)
                intent.putExtra("result", result)
                startActivity(intent)
            }

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }
}