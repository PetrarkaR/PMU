package com.example.triujednojpokusaj3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation

class ScoreFragment : Fragment() {
    private lateinit var btnNavigationFragmentPocetni: Button
    private lateinit var textScore: TextView
        private lateinit var textName: TextView
    private val scoreViewModel: ScoreViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_score, container, false)
        textScore = view.findViewById(R.id.tekstScore)
        textName = view.findViewById(R.id.textName)
        btnNavigationFragmentPocetni = view.findViewById(R.id.buttonBackAbout)

        scoreViewModel.gameName.observe(viewLifecycleOwner) { name ->
            textName.text = name
        }
        scoreViewModel.score.observe(viewLifecycleOwner) { score ->
            textScore.text = score
        }


        btnNavigationFragmentPocetni.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_scoreFragment_to_pocetniFragment)
        }





        return view


    }
}