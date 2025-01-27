package com.example.triujednojpokusaj3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IksViewModel : ViewModel() {
    private val _gameState = MutableLiveData<GameState>()
    val gameState: LiveData<GameState> = _gameState

    private val _score = MutableLiveData<Score>()
    val score: LiveData<Score> = _score

    private val _currentPlayer = MutableLiveData<Player>()
    val currentPlayer: LiveData<Player> = _currentPlayer

    private val _gameStatus = MutableLiveData<GameStatus>()
    val gameStatus: LiveData<GameStatus> = _gameStatus

    init {
        initializeGame()
    }

    private fun initializeGame() {
        _gameState.value = GameState()
        _score.value = Score()
        _currentPlayer.value = Player.X
        _gameStatus.value = GameStatus.IN_PROGRESS
    }

    fun makeMove(row: Int, col: Int) {
        if (_gameStatus.value == GameStatus.GAME_OVER) return
        if (_gameState.value?.board?.get(row)?.get(col) != null) return

        val currentBoard = _gameState.value?.board?.map { it.toMutableList() }?.toMutableList()
            ?: return
        val currentPlayerValue = _currentPlayer.value ?: return

        currentBoard[row][col] = currentPlayerValue
        _gameState.value = GameState(currentBoard)

        when {
            checkWin(currentBoard, currentPlayerValue) -> handleWin(currentPlayerValue)
            checkDraw(currentBoard) -> handleDraw()
            else -> switchPlayer()
        }
    }

    private fun handleWin(winner: Player) {
        val currentScore = _score.value ?: Score()
        _score.value = when (winner) {
            Player.X -> currentScore.copy(xWins = currentScore.xWins + 1)
            Player.O -> currentScore.copy(oWins = currentScore.oWins + 1)
        }
        _gameStatus.value = GameStatus.GAME_OVER
    }

    private fun handleDraw() {
        val currentScore = _score.value ?: Score()
        _score.value = currentScore.copy(draws = currentScore.draws + 1)
        _gameStatus.value = GameStatus.GAME_OVER
    }

    private fun switchPlayer() {
        _currentPlayer.value = when (_currentPlayer.value) {
            Player.X -> Player.O
            Player.O -> Player.X
            null -> Player.X
        }
    }

    fun resetGame() {
        _gameState.value = GameState()
        _currentPlayer.value = Player.X
        _gameStatus.value = GameStatus.IN_PROGRESS
    }

    fun resetScore() {
        _score.value = Score()
    }

    private fun checkWin(board: List<List<Player?>>, player: Player): Boolean {
        // Check rows
        for (row in board) {
            if (row.all { it == player }) return true
        }

        // Check columns
        for (col in board.indices) {
            if (board.all { it[col] == player }) return true
        }

        // Check diagonals
        val mainDiagonal = (board.indices).all { board[it][it] == player }
        val antiDiagonal = (board.indices).all { board[it][board.size - 1 - it] == player }

        return mainDiagonal || antiDiagonal
    }

    private fun checkDraw(board: List<List<Player?>>): Boolean {
        return board.all { row -> row.all { it != null } }
    }
}

// Data classes and enums
enum class Player {
    X, O
}

enum class GameStatus {
    IN_PROGRESS, GAME_OVER
}

data class Score(
    val xWins: Int = 0,
    val oWins: Int = 0,
    val draws: Int = 0
)

data class GameState(
    val board: List<List<Player?>> = List(3) { List(3) { null } }
)
