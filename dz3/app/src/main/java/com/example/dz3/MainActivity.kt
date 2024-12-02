package com.example.dz3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var isXTurn = true
    private lateinit var statusText: TextView
    private lateinit var scoreBoard: TextView
    private lateinit var resetButton: Button
    private lateinit var resetScore : Button
    private val cells = Array(3) { Array<EditText?>(3) { null } }

    // Score tracking: X wins : O wins : Draws
    private var xWins = 0
    private var oWins = 0
    private var draws = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize the game
        initializeGame()
    }

    private fun initializeGame() {
        statusText = findViewById(R.id.statusText)
        scoreBoard = findViewById(R.id.scoreBoard)
        resetButton = findViewById(R.id.resetButton)
        resetScore = findViewById(R.id.resetScore)
        // Initialize all cells
        for (i in 0..2) {
            for (j in 0..2) {
                val cellId = resources.getIdentifier("cell$i$j", "id", packageName)
                cells[i][j] = findViewById(cellId)
                cells[i][j]?.addTextChangedListener(createTextWatcher(i, j))
            }
        }

        // Set up reset button
        resetButton.setOnClickListener {
            resetGame()
        }
        resetScore.setOnClickListener {
            resetScore()
        }
        updateStatusText()
        updateScoreBoard()
    }

    private fun createTextWatcher(row: Int, col: Int) = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            val input = s.toString().uppercase()
            if (input.isNotEmpty()) {
                // Validate move
                if ((isXTurn && input != "X") || (!isXTurn && input != "O")) {
                    cells[row][col]?.setText(if (isXTurn) "X" else "O")
                    return
                }

                // Disable the cell after a valid move
                cells[row][col]?.isEnabled = false

                // Check for win or draw
                if (checkWin(row, col)) {
                    if (isXTurn) xWins++ else oWins++
                    updateScoreBoard()
                    gameOver("${if (isXTurn) "X" else "O"} je pobedio!")
                } else if (checkDraw()) {
                    draws++
                    updateScoreBoard()
                    gameOver("nereseno, ko bi rekao!")
                } else {
                    // Switch turns
                    isXTurn = !isXTurn
                    updateStatusText()
                }
            }
        }
    }

    private fun checkWin(row: Int, col: Int): Boolean {
        val symbol = if (isXTurn) "X" else "O"

        // Check row
        if (cells[row].all { it?.text.toString() == symbol }) return true

        // Check column
        if (cells.all { it[col]?.text.toString() == symbol }) return true

        // Check diagonals
        if (row == col || row + col == 2) {
            // Main diagonal
            if (cells[0][0]?.text.toString() == symbol &&
                cells[1][1]?.text.toString() == symbol &&
                cells[2][2]?.text.toString() == symbol) return true

            // Anti-diagonal
            if (cells[0][2]?.text.toString() == symbol &&
                cells[1][1]?.text.toString() == symbol &&
                cells[2][0]?.text.toString() == symbol) return true
        }

        return false
    }

    private fun checkDraw(): Boolean {
        return cells.all { row -> row.all { it?.text?.isNotEmpty() == true } }
    }

    private fun gameOver(message: String) {
        statusText.text = message
        // Disable all cells
        cells.forEach { row ->
            row.forEach { cell ->
                cell?.isEnabled = false
            }
        }
    }

    private fun updateStatusText() {
        statusText.text = if (isXTurn) "x na potezu" else "o na potezu"
    }

    private fun updateScoreBoard() {
        scoreBoard.text = "$xWins:$oWins:$draws"
    }

    private fun resetGame() {
        // Clear and enable all cells
        cells.forEach { row ->
            row.forEach { cell ->
                cell?.apply {
                    setText("")
                    isEnabled = true
                }
            }
        }

        // Reset turn to X
        isXTurn = true

        // Update status
        updateStatusText()
    }
    private fun resetScore(){

        scoreBoard.text = "0:0:0"
        oWins=0
        xWins=0
        draws=0
    }
}