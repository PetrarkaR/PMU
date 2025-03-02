package com.example.dz6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation

class AboutFragment : Fragment() {
    private lateinit var btnNavigationFragmentPocetni: Button



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_about, container, false)

        btnNavigationFragmentPocetni = view.findViewById(R.id.buttonBackAbout)
        btnNavigationFragmentPocetni.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_aboutFragment_to_pocetniFragment)
        }
        return view;
    }
}