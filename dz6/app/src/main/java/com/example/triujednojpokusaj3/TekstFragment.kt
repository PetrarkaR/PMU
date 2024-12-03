package com.example.triujednojpokusaj3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController

class TekstFragment : Fragment() {
    private lateinit var textInput: EditText
    private lateinit var displayText: TextView
    private lateinit var submitButton: Button
    private lateinit var backButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tekst, container, false)

        // Initialize views
        initializeViews(view)
        setupClickListeners()

        return view
    }

    private fun initializeViews(view: View) {
        textInput = view.findViewById(R.id.input)
        displayText = view.findViewById(R.id.displayText)
        submitButton = view.findViewById(R.id.button)
        backButton = view.findViewById(R.id.backbutton)

        backButton.setOnClickListener {
            findNavController().navigateUp()  // This will navigate back to the previous fragment
        }
    }

    private fun setupClickListeners() {
        submitButton.setOnClickListener {
            val inputText = textInput.text.toString().trim()
            displayText.text = when (inputText.lowercase()) {
                "opis" -> vars.ORIGAMI_PROCESS + "\n" + vars.ORIGAMI_PROCESS + "\n" + vars.ORIGAMI_PROCESS
                "istorija" -> vars.ORIGAMI_HISTORY + "\n" + vars.ORIGAMI_HISTORY
                "danas" -> vars.ORIGAMI_TODAY + "\n" + vars.ORIGAMI_TODAY
                else -> "Nema informacija za uneti tekst."
            }
        }

        backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    companion object {
        // If you need to create instances with specific parameters
        @JvmStatic
        fun newInstance() = TekstFragment()
    }
}