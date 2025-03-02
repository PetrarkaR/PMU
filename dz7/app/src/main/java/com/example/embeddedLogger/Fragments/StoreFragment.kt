package com.example.embeddedLogger.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.embeddedLogger.R
import com.example.embeddedLogger.ViewModels.StoreViewModel
import com.example.embeddedLogger.ViewModels.StoreViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StoreFragment : Fragment() {
    private lateinit var btnNavigationFragmentPocetni: FloatingActionButton
    private lateinit var storeViewModel: StoreViewModel
    private lateinit var btnClear: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_store, container, false)
        val textView = view.findViewById<TextView>(R.id.textView2)
        btnNavigationFragmentPocetni = view.findViewById(R.id.floatingbutton)
        btnClear = view.findViewById(R.id.buttonClear)

        // Use StoreViewModelFactory to get StoreViewModel
        val factory = StoreViewModelFactory(requireActivity().application)
        storeViewModel = ViewModelProvider(this, factory).get(StoreViewModel::class.java)

        // Observe LiveData from ViewModel
        storeViewModel.allItems.observe(viewLifecycleOwner) { items ->
            val displayText = items.joinToString(separator = "\n") {
                "${it.name} - \"${it.category}\" - ${it.price}%"
            }
            textView.text = displayText
        }

        // Navigation to AddFragment
        btnNavigationFragmentPocetni.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_storeFragment_to_addFragment)
        }

        btnClear.setOnClickListener {
            storeViewModel.clearAllItems()
        }



        return view
    }
}
