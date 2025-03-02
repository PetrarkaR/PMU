package com.example.dz6

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class KockiceViewModel : ViewModel() {
    private val _diceImage1 = MutableLiveData<Int>()
    val diceImage1: LiveData<Int> get() = _diceImage1

    private val _diceImage2 = MutableLiveData<Int>()
    val diceImage2: LiveData<Int> get() = _diceImage2

    private val _resultText = MutableLiveData<String>()
    val resultText: LiveData<String> get() = _resultText

    fun rollDice() {
        val randomInt1 = Random.nextInt(1, 7)
        val randomInt2 = Random.nextInt(1, 7)

        _diceImage1.value = getDiceImage(randomInt1)
        _diceImage2.value = getDiceImage(randomInt2)
        _resultText.value = (randomInt1 + randomInt2).toString()
    }

    private fun getDiceImage(number: Int): Int {
        return when (number) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }
}