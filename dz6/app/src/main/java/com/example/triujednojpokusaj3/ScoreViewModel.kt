package com.example.triujednojpokusaj3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel: ViewModel() {
    private val _gameName = MutableLiveData<String>()
    val gameName: LiveData<String> get() = _gameName

    private val _score = MutableLiveData<String>()
    val score: LiveData<String> get() = _score

    // Function to set the game name and score
    fun setGameData(name: String, score: String) {
        _gameName.value = name
        _score.value = score
    }

}