package com.example.dz2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.dz2.vars

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the EditText, TextView, and Button
        val textInput = findViewById<EditText>(R.id.input)
        val displayText = findViewById<TextView>(R.id.displayText)
        val submitButton = findViewById<Button>(R.id.button)

        // Set an OnClickListener for the Button
        submitButton.setOnClickListener {
            val inputText = textInput.text.toString().trim()
            displayText.text = when (inputText.lowercase()) {
                "opis" -> vars.ORIGAMI_PROCESS + "\n" + vars.ORIGAMI_PROCESS + "\n" + vars.ORIGAMI_PROCESS // Repeat to test scrolling
                "istorija" -> vars.ORIGAMI_HISTORY + "\n" + vars.ORIGAMI_HISTORY // Repeat to test scrolling
                "danas" -> vars.ORIGAMI_TODAY + "\n" + vars.ORIGAMI_TODAY // Repeat to test scrolling
                else -> "Nema informacija za uneti tekst."
            }
        }
    }
}

