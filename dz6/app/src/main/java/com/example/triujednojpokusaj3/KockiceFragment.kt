package com.example.triujednojpokusaj3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation


class KockiceFragment : Fragment() {
    private lateinit var btnNavigationFragmentPocetni: Button
    private lateinit var diceImage: ImageView
    private lateinit var diceImage2: ImageView
    private lateinit var resultText: TextView
    private lateinit var scoreButton: Button

    private val viewModel: KockiceViewModel by viewModels()
    private val scoreViewModel: ScoreViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_kockice, container, false)

        btnNavigationFragmentPocetni = view.findViewById(R.id.buttonBack)
        diceImage = view.findViewById(R.id.dice_image)
        diceImage2 = view.findViewById(R.id.dice_image2)
        resultText = view.findViewById(R.id.result_text)
        scoreButton = view.findViewById(R.id.rezultatButton2)
        val rollButton: Button = view.findViewById(R.id.roll_button)






        rollButton.text = "Kockanje!"

        btnNavigationFragmentPocetni.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_kockiceFragment_to_pocetniFragment)
        }

        viewModel.diceImage1.observe(viewLifecycleOwner, Observer { drawableResource ->
            diceImage.setImageResource(drawableResource)
        })

        viewModel.diceImage2.observe(viewLifecycleOwner, Observer { drawableResource ->
            diceImage2.setImageResource(drawableResource)
        })

        viewModel.resultText.observe(viewLifecycleOwner, Observer { result ->
            resultText.text = result
            scoreViewModel.setGameData("Monopol", "$result")

        })


        rollButton.setOnClickListener {
            viewModel.rollDice()
        }
        scoreButton.setOnClickListener(){

            Navigation.findNavController(view).navigate(R.id.action_kockiceFragment_to_scoreFragment)
        }


        return view
    }
}



