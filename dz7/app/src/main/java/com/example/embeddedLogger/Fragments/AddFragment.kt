package com.example.embeddedLogger.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.embeddedLogger.R
import com.example.embeddedLogger.ViewModels.AddViewModel
import com.example.embeddedLogger.ViewModels.AddViewModelFactory

class AddFragment : Fragment() {

    private lateinit var btnNavigation: Button
    private lateinit var addViewModel: AddViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_add, container, false)

        btnNavigation = view.findViewById(R.id.buttonSave)
        val nameEditText = view.findViewById<EditText>(R.id.editTextName)
        val categoryEditText = view.findViewById<EditText>(R.id.editTextCategory)
        val priceEditText = view.findViewById<EditText>(R.id.editTextPrice)

        // Using the ViewModelFactory to instantiate the ViewModel
        val factory = AddViewModelFactory(requireActivity().application)
        addViewModel = ViewModelProvider(this, factory).get(AddViewModel::class.java)

        btnNavigation.setOnClickListener {
            val name = nameEditText.text.toString()
            val category = categoryEditText.text.toString()
            val price = priceEditText.text.toString().toDoubleOrNull()

            addViewModel.insertItem(name, category, price.toString())
            Navigation.findNavController(view).navigate(R.id.action_addFragment_to_storeFragment)
        }

        return view
    }
}