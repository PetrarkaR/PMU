package com.example.dz6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI


class PocetniFragment : Fragment() {
    private lateinit var btnNavigationFragmentPocetni: Button
    private lateinit var btnNavigationFragmentIks: Button
    private lateinit var btnNavigationFragmentTekst: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_pocetni, container, false)

        btnNavigationFragmentPocetni = view.findViewById(R.id.buttonKockice)
        btnNavigationFragmentPocetni.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_pocetniFragment_to_kockiceFragment)
        }
        btnNavigationFragmentIks = view.findViewById(R.id.buttonIks)
        btnNavigationFragmentIks.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_pocetniFragment_to_iksFragment)
        }
        btnNavigationFragmentTekst = view.findViewById(R.id.buttonTekst)
        btnNavigationFragmentTekst.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_pocetniFragment_to_tekstFragment)
        }
        setHasOptionsMenu(true)

        return view;
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflow_menu,menu) }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,
            requireView().findNavController())
                || super.onOptionsItemSelected(item)}
}