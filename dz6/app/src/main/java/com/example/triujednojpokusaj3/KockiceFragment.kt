package com.example.triujednojpokusaj3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import java.util.Random

class KockiceFragment : Fragment() {
    private lateinit var dice1: ImageView
    private lateinit var dice2: ImageView
    private lateinit var rollButton: Button
    private lateinit var backButton: Button
    private lateinit var sumText: TextView
    private lateinit var buttonSwitch: Switch

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_kockice, container, false)

        initializeViews(view)
        setupClickListeners()

        return view
    }

    private fun initializeViews(view: View) {
        dice1 = view.findViewById(R.id.dice1)
        dice2 = view.findViewById(R.id.dice2)
        rollButton = view.findViewById(R.id.button)
        backButton = view.findViewById(R.id.backbutton)
        sumText = view.findViewById(R.id.sum)
        buttonSwitch = view.findViewById(R.id.buttonSwitch)
    }

    private fun setupClickListeners() {
        rollButton.setOnClickListener {
            if (!buttonSwitch.isChecked) {
                rollDice()
            }
        }

        backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        buttonSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                rollButton.isEnabled = false
                sumText.text = "Kockanje zaustavljeno!"
            } else {
                rollButton.isEnabled = true
                sumText.text = ""
            }
        }
    }

    private fun rollDice() {
        val randomInt1 = Random().nextInt(6) + 1
        val drawableResource1 = when (randomInt1) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        dice1.setImageResource(drawableResource1)

        val randomInt2 = Random().nextInt(6) + 1
        val drawableResource2 = when (randomInt2) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        dice2.setImageResource(drawableResource2)

        val sum = randomInt1 + randomInt2
        sumText.text = sum.toString()
    }
}