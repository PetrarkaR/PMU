package com.example.triujednojpokusaj3

import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import java.util.concurrent.TimeUnit

class IksFragment : Fragment() {
    private lateinit var statusText: TextView
    private lateinit var scoreBoard: TextView
    private lateinit var resetButton: Button
    private lateinit var resetScore: Button
    private lateinit var backbutton: Button
    private lateinit var timerText: TextView
    private lateinit var countDownTimer: CountDownTimer

    private val cells = Array(3) { Array<EditText?>(3) { null } }

    // ViewModel
    private val viewModel: TicTacToeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_iks, container, false)

        // Initialize views after inflation
        initializeViews(view)
        initializeGame()
        observeViewModel()
        startTimer()

        return view
    }

    private fun initializeViews(view: View) {
        statusText = view.findViewById(R.id.statusText)
        scoreBoard = view.findViewById(R.id.scoreBoard)
        resetButton = view.findViewById(R.id.resetButton)
        resetScore = view.findViewById(R.id.resetScore)
        backbutton = view.findViewById(R.id.backbutton)
        timerText = view.findViewById(R.id.timerText)

        backbutton.setOnClickListener {
            findNavController().navigateUp()
        }

        // Initialize all cells
        for (i in 0..2) {
            for (j in 0..2) {
                val cellId = resources.getIdentifier("cell$i$j", "id", requireContext().packageName)
                cells[i][j] = view.findViewById(cellId)
                cells[i][j]?.addTextChangedListener(createTextWatcher(i, j))
            }
        }
    }

    private fun initializeGame() {
        // Set up reset buttons
        resetButton.setOnClickListener {
            resetGame()
        }
        resetScore.setOnClickListener {
            resetScore()
        }
    }

    private fun observeViewModel() {
        viewModel.isXTurn.observe(viewLifecycleOwner) { isXTurn ->
            updateStatusText(isXTurn)
        }

        viewModel.xWins.observe(viewLifecycleOwner) { _ ->
            updateScoreBoard()
        }
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(6000, 1000) { // 60 seconds, tick every 1 second
            override fun onTick(millisUntilFinished: Long) {
                val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                val seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60
                timerText.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                timerText.text = "00:00"
                Toast.makeText(context, "Vreme je isteklo!", Toast.LENGTH_SHORT).show()
                gameOver("Vreme je isteklo!")
            }
        }.start()
    }

    private fun createTextWatcher(row: Int, col: Int) = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            val input = s.toString().uppercase()
            if (input.isNotEmpty()) {
                // Get current game state
                val currentBoard = viewModel.boardState.value ?: Array(3) { Array(3) { "" } }
                val currentTurn = viewModel.isXTurn.value ?: true

                // Validate move
                if ((currentTurn && input != "X") || (!currentTurn && input != "O")) {
                    cells[row][col]?.setText(if (currentTurn) "X" else "O")
                    return
                }

                // Update board state
                currentBoard[row][col] = input
                viewModel.updateBoardState(row, col, input)

                // Disable the cell after a valid move
                cells[row][col]?.isEnabled = false

                // Check for win or draw
                if (viewModel.checkWin(currentBoard, currentTurn)) {
                    viewModel.recordWin(currentTurn)
                    gameOver("${if (currentTurn) "X" else "O"} je pobedio!")
                } else if (viewModel.checkDraw(currentBoard)) {
                    viewModel.recordDraw()
                    gameOver("Nereseno!")
                } else {
                    // Switch turns in ViewModel
                    viewModel.switchTurn()
                }
            }
        }
    }

    // Rest of the methods remain the same as in the previous implementation...

    private fun updateStatusText(isXTurn: Boolean) {
        statusText.text = if (isXTurn) "x na potezu" else "o na potezu"
    }

    private fun updateScoreBoard() {
        val xWins = viewModel.xWins.value ?: 0
        val oWins = viewModel.oWins.value ?: 0
        val draws = viewModel.draws.value ?: 0
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

        // Reset game in ViewModel
        viewModel.resetGame()

        // Restart timer
        countDownTimer.cancel()
        startTimer()
    }

    private fun resetScore() {
        viewModel.resetScore()
        countDownTimer.cancel()
        startTimer()
    }

    private fun gameOver(message: String) {
        statusText.text = message
        // Disable all cells
        cells.forEach { row ->
            row.forEach { cell ->
                cell?.isEnabled = false
            }
        }
        countDownTimer.cancel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        countDownTimer.cancel()
    }
    private lateinit var timer: CountDownTimer
    private val turnTimeLimit: Long = 10_000 // 10 seconds

    private fun startTurnTimer() {
        timer = object : CountDownTimer(turnTimeLimit, 1_000) {
            override fun onTick(millisUntilFinished: Long) {
                statusText.text = "${if (viewModel.isXTurn.value == true) "X" else "O"} na potezu - preostalo vreme: ${millisUntilFinished / 1000}s"
            }

            override fun onFinish() {
                Toast.makeText(requireContext(), "Vreme je isteklo!", Toast.LENGTH_SHORT).show()
                viewModel.isXTurn.value = !(viewModel.isXTurn.value ?: true) // Switch turn
                startTurnTimer() // Restart timer for the next turn
            }
        }
        timer.start()
    }



    // Existing win and draw check methods remain the same
}