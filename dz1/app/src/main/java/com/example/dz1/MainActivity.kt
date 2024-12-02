package com.example.dz1

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dz1.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var diceImage: ImageView
        lateinit var rollButtonSwitch: Switch
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setContentView(R.layout.activity_main)
        diceImage = findViewById(R.id.dice1)
        rollButtonSwitch= findViewById(R.id.buttonSwitch)
        val rollButton: Button = findViewById(R.id.button)
        rollButton.text = "Kockanje!"
        rollButton.setOnClickListener {rollDice()}
        rollButtonSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            rollButton.isEnabled = !isChecked
        }


    }

    private fun rollDice() {
        val rollButton: Button = findViewById(R.id.button)
        val resultText: TextView = findViewById(R.id.sum)
        val randomInt1 = java.util.Random().nextInt(6)+1
        val randomInt2 = java.util.Random().nextInt(6)+1
        val suma=randomInt1+randomInt2
//        resultText.text = suma.toString()
        resultText.text = "Zbir je: $suma"
        rollButton.isSelected != rollButton.isSelected

        val diceImage1: ImageView = findViewById(R.id.dice1)
        val diceImage2: ImageView = findViewById(R.id.dice2)
        val drawableResource1 = when (randomInt1) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6 }
        val drawableResource2 = when (randomInt2) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6 }
        diceImage1.setImageResource(drawableResource1)
        diceImage2.setImageResource(drawableResource2)

    }
}