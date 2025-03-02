package com.example.dz6

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

class TekstFragment : Fragment() {

    private lateinit var btnNavigationFragmentPocetni: Button


    private val tekstViewModel: TekstViewModel by viewModels()
    private val scoreViewModel: ScoreViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_tekst, container, false)

        val button: Button = view.findViewById(R.id.button)
        val slide: TextView = view.findViewById(R.id.textView5)
        val choose: TextView = view.findViewById(R.id.with_number)
        val scoreButton: Button = view.findViewById(R.id.rezultatButton2)



        scoreButton.setOnClickListener(){
            Navigation.findNavController(view).navigate(R.id.action_tekstFragment_to_scoreFragment)
        }

        btnNavigationFragmentPocetni = view.findViewById(R.id.buttonBack4)
        btnNavigationFragmentPocetni.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_tekstFragment_to_pocetniFragment)
        }

        tekstViewModel.slideText.observe(viewLifecycleOwner, { text ->
            slide.text = text
        })
        button.setOnClickListener {
            val chooseString = choose.text.toString()
            tekstViewModel.updateText(chooseString)
            if(chooseString == "1")
                scoreViewModel.setGameData("Choose Text", "opis")
            else if(chooseString == "2")
                scoreViewModel.setGameData("Choose Text", "istorija")
            else if(chooseString == "3")
                scoreViewModel.setGameData("Choose Text", "danas")


        }



        return view
    }
}
