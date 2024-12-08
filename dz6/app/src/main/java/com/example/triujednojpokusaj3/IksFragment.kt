package com.example.triujednojpokusaj3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation

class IksFragment : Fragment() {

    // ViewModel for managing game state
    private val iksViewModel: IksViewModel by viewModels()
    private val scoreViewModel: ScoreViewModel by activityViewModels()


    private lateinit var btnNavigationFragmentPocetni: Button
    private lateinit var textViews: List<TextView>
    private lateinit var winnerTextView: TextView
    private lateinit var redButton: Button
    private lateinit var blueButton: Button
    private lateinit var hintInput: EditText
    private lateinit var resetButton: Button
    private lateinit var timerTextView: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_iks, container, false)
         val scoreButton: Button

        btnNavigationFragmentPocetni = view.findViewById(R.id.buttonBack2)
        winnerTextView = view.findViewById(R.id.lastwinner)
        redButton = view.findViewById(R.id.redButton)
        blueButton = view.findViewById(R.id.blueButton)
        hintInput = view.findViewById(R.id.choose2)
        resetButton = view.findViewById(R.id.button)
        timerTextView = view.findViewById(R.id.timerTextView)
        scoreButton = view.findViewById(R.id.rezultatButton2)

        scoreButton.setOnClickListener(){
            scoreViewModel.setGameData("X/O", "${winnerTextView.text}")
            Navigation.findNavController(view).navigate(R.id.action_iksFragment_to_scoreFragment)

        }

        textViews = listOf(
            view.findViewById(R.id.P1),
            view.findViewById(R.id.P2),
            view.findViewById(R.id.p3),
            view.findViewById(R.id.p4),
            view.findViewById(R.id.p5),
            view.findViewById(R.id.p6),
            view.findViewById(R.id.p7),
            view.findViewById(R.id.p8),
            view.findViewById(R.id.p9)
        )
        textViews.forEach{ it.tag = "not_clicked" }
        btnNavigationFragmentPocetni.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_iksFragment_to_pocetniFragment)
        }
iksViewModel.startTimer()
        iksViewModel.timeRemaining.observe(viewLifecycleOwner, Observer { timeRemaining ->
            if (timeRemaining == 0L) {
                timerTextView.text = "Time's up!"
            } else {
                timerTextView.text = "Time remaining: $timeRemaining seconds"
            }
        })

        iksViewModel.winnerText.observe(viewLifecycleOwner, {
            winnerTextView.text = it
        })

        resetButton.setOnClickListener {
            iksViewModel.gameRestart(textViews)
            iksViewModel.startTimer()
            scoreViewModel.setGameData("X/O", "${winnerTextView.text}")
        }

        redButton.setOnClickListener {
            val hint = hintInput.text.toString()
            iksViewModel.handleMove(textViews, hint, 1)
            scoreViewModel.setGameData("X/O", "${winnerTextView.text}")
        }

        blueButton.setOnClickListener {
            val hint = hintInput.text.toString()
            iksViewModel.handleMove(textViews, hint, 2)
            scoreViewModel.setGameData("X/O", "${winnerTextView.text}")
        }
        winnerTextView.setOnClickListener{
            iksViewModel.resetWinner()
            scoreViewModel.setGameData("X/O", "${winnerTextView.text}")
        }

        return view
    }
}