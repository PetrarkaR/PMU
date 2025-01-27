// IksFragment.kt
package com.example.triujednojpokusaj3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

class IksFragment : Fragment() {
    private val viewModel: IksViewModel by viewModels()
    private val cells = Array(3) { Array<EditText?>(3) { null } }
    private lateinit var statusText: TextView
    private lateinit var scoreBoard: TextView
    private lateinit var resetButton: Button
    private lateinit var resetScore: Button
    private lateinit var backButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_iks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews(view)
        setupObservers()
        setupClickListeners()
    }

    private fun initializeViews(view: View) {
        statusText = view.findViewById(R.id.statusText)
        scoreBoard = view.findViewById(R.id.scoreBoard)
        resetButton = view.findViewById(R.id.resetButton)
        resetScore = view.findViewById(R.id.resetScore)
        backButton = view.findViewById(R.id.backbutton)

        for (i in 0..2) {
            for (j in 0..2) {
                val cellId = resources.getIdentifier("cell$i$j", "id", requireContext().packageName)
                cells[i][j] = view.findViewById(cellId)
            }
        }
    }

    private fun setupObservers() {
        viewModel.gameState.observe(viewLifecycleOwner) { gameState ->
            updateBoard(gameState)
        }

        viewModel.score.observe(viewLifecycleOwner) { score ->
            scoreBoard.text = "${score.xWins}:${score.oWins}:${score.draws}"
        }

        viewModel.currentPlayer.observe(viewLifecycleOwner) { player ->
            statusText.text = "${player.name} na potezu"
        }

        viewModel.gameStatus.observe(viewLifecycleOwner) { status ->
            when (status) {
                GameStatus.GAME_OVER -> {
                    disableAllCells()
                    viewModel.currentPlayer.value?.let { winner ->
                        statusText.text = "${winner.name} je pobedio!"
                    }
                }
                GameStatus.IN_PROGRESS -> enableEmptyCells()
            }
        }
    }

    private fun setupClickListeners() {
        for (i in 0..2) {
            for (j in 0..2) {
                cells[i][j]?.setOnClickListener {
                    viewModel.makeMove(i, j)
                }
            }
        }

        resetButton.setOnClickListener { viewModel.resetGame() }
        resetScore.setOnClickListener { viewModel.resetScore() }
        backButton.setOnClickListener { findNavController().navigateUp() }
    }

    private fun updateBoard(gameState: GameState) {
        for (i in 0..2) {
            for (j in 0..2) {
                cells[i][j]?.setText(gameState.board[i][j]?.name ?: "")
            }
        }
    }

    private fun disableAllCells() {
        cells.forEach { row ->
            row.forEach { cell ->
                cell?.isEnabled = false
            }
        }
    }

    private fun enableEmptyCells() {
        cells.forEach { row ->
            row.forEach { cell ->
                cell?.isEnabled = cell?.text?.isEmpty() ?: false
            }
        }
    }
}
