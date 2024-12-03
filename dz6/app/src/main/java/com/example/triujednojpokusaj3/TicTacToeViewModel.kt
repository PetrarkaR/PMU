package com.example.triujednojpokusaj3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TicTacToeViewModel : ViewModel() {
    // Encapsulated game state properties
    private val _isXTurn = MutableLiveData(true) // Encapsulated MutableLiveData
    val isXTurn: LiveData<Boolean> get() = _isXTurn // Exposed as LiveData

    fun switchTurn() {
        _isXTurn.value = !(_isXTurn.value ?: true)
    }
    private val _xWins = MutableLiveData(0)
    val xWins: LiveData<Int> = _xWins

    private val _oWins = MutableLiveData(0)
    val oWins: LiveData<Int> = _oWins

    private val _draws = MutableLiveData(0)
    val draws: LiveData<Int> = _draws

    // 2D Array to represent game board state
    private val _boardState = MutableLiveData(Array(3) { Array(3) { "" } })
    val boardState: LiveData<Array<Array<String>>> = _boardState

    // Method to update turn

    // Method to update board state
    fun updateBoardState(row: Int, col: Int, value: String) {
        val currentBoard = _boardState.value?.map { it.clone() }?.toTypedArray() ?: Array(3) { Array(3) { "" } }
        currentBoard[row][col] = value
        _boardState.value = currentBoard
    }

    // Method to record win
    fun recordWin(isXWin: Boolean) {
        if (isXWin) {
            _xWins.value = (_xWins.value ?: 0) + 1
        } else {
            _oWins.value = (_oWins.value ?: 0) + 1
        }
    }

    // Method to record draw
    fun recordDraw() {
        _draws.value = (_draws.value ?: 0) + 1
    }

    // Method to reset game board
    fun resetGame() {
        _boardState.value = Array(3) { Array(3) { "" } }
        _isXTurn.value = true
    }

    // Method to reset score
    fun resetScore() {
        _xWins.value = 0
        _oWins.value = 0
        _draws.value = 0
    }
    // Method to check for a win
    fun checkWin(boardState: Array<Array<String>>, isXTurn: Boolean): Boolean {
        val symbol = if (isXTurn) "X" else "O"

        // Check rows
        for (row in 0..2) {
            if (boardState[row].all { it == symbol }) return true
        }

        // Check columns
        for (col in 0..2) {
            if (boardState.all { it[col] == symbol }) return true
        }

        // Check diagonals
        if (boardState[0][0] == symbol &&
            boardState[1][1] == symbol &&
            boardState[2][2] == symbol) return true

        if (boardState[0][2] == symbol &&
            boardState[1][1] == symbol &&
            boardState[2][0] == symbol) return true

        return false
    }

    // Method to check for a draw
    fun checkDraw(boardState: Array<Array<String>>): Boolean {
        return boardState.all { row -> row.all { it.isNotEmpty() } }
    }
}