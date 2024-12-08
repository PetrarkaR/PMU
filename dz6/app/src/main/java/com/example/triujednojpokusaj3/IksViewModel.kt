package com.example.triujednojpokusaj3

import android.graphics.Color
import android.os.CountDownTimer
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IksViewModel : ViewModel() {
//    companion object {
//
//
//        // These represent different important times
//// This is when the game is over
//        const val DONE = 0L
//        // This is the number of milliseconds in a second
//        const val ONE_SECOND = 1000L
//        // This is the total time of the game
//        const val timeRemaining = 60000L
//    }


    private var x = 0
    private var o = 0

    val winnerText = MutableLiveData<String>()
    val turnCounter = MutableLiveData<Int>().apply { value = 0 }

    private val _timeRemaining = MutableLiveData<Long>()
    val timeRemaining: LiveData<Long> = _timeRemaining
    private var countDownTimer: CountDownTimer? = null



    fun gameRestart(textViews: List<TextView>) {
        textViews.forEach { it.setBackgroundColor(Color.TRANSPARENT)
            it.setTextColor(Color.WHITE)
            it.tag = "not_clicked"
//            it.setText("P")
        }
        textViews.forEachIndexed { index, textView ->
            val rowNumber = index + 1
            textView.text = "P$rowNumber"
        }
        winnerText.value = "X:O\n" +
                "$x:$o"
    }

    fun resetWinner() {
        x = 0
        o = 0
        winnerText.value = "X:O\n $x:$o"
    }

    fun handleMove(textViews: List<TextView>, hint: String, turn: Int) {
        textViews.forEach { textView ->
            if (textView.text.toString() == hint && textView.tag == "not_clicked") {
                if (turn == 1) {
                    textView.setBackgroundColor(Color.RED)
                    textView.setTextColor(Color.WHITE)
                    textView.setText("X")
                } else {
                    textView.setBackgroundColor(Color.BLUE)
                    textView.setTextColor(Color.WHITE)
                    textView.setText("O")
                }
                textView.tag = "clicked"
                turnCounter.value = if (turn == 1) 2 else 1


                checkForWinner(textViews)
            }
        }
    }

    private fun checkForWinner(textViews: List<TextView>) {
        for (i in 0..2) {
            val start = i * 3
            if (textViews[start].text == textViews[start + 1].text &&
                textViews[start].text == textViews[start + 2].text &&
                textViews[start].text.isNotEmpty()
            ) {
                showWinner(textViews[start].text.toString())
                return
            }
        }

        for (i in 0..2) {
            if (textViews[i].text == textViews[i + 3].text &&
                textViews[i].text == textViews[i + 6].text &&
                textViews[i].text.isNotEmpty()
            ) {
                showWinner(textViews[i].text.toString())
                return
            }
        }

        if (textViews[0].text == textViews[4].text && textViews[0].text == textViews[8].text) {
            showWinner(textViews[0].text.toString())
        } else if (textViews[2].text == textViews[4].text && textViews[2].text == textViews[6].text) {
            showWinner(textViews[2].text.toString())
        }
    }

    private fun showWinner(winner: String) {
        winnerText.value = "WINNER: $winner"
        updateScore(winner)
        cancelTimer()
    }

    private fun updateScore(winner: String) {
        if (winner == "X") {
            x++
        } else if (winner == "O") {
            o++
        }
        winnerText.value = "X:O\n $x:$o"
    }


    fun startTimer() {
        val initialTime = 60000L // 10 seconds
        countDownTimer = object : CountDownTimer(initialTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _timeRemaining.value = millisUntilFinished / 1000 // Update the time remaining
            }

            override fun onFinish() {
                _timeRemaining.value = 0 // Timer finished
            }
        }
        countDownTimer?.start() // Start the timer
    }

    fun cancelTimer() {
        countDownTimer?.cancel() // Cancel the timer if it's running
        _timeRemaining.value = 0 // Reset the timer display
    }

    override fun onCleared() {
        super.onCleared()
        countDownTimer?.cancel() // Ensure the timer is canceled when ViewModel is destroyed
    }
}

