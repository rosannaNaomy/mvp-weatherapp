package com.example.myweatherapp.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myweatherapp.R


class SearchFragment : Fragment() {

    private lateinit var searchFragmentView: View
    private lateinit var submitButton: Button
    private lateinit var editText: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchFragmentView =
            LayoutInflater.from(context).inflate(R.layout.fragment_search, container, false)
        return searchFragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = ""
        (activity as AppCompatActivity).supportActionBar?.show()

        submitButton = searchFragmentView.findViewById(R.id.submitcity_search_button)
        editText = searchFragmentView.findViewById(R.id.lookUpCity_editText)

        submitButton.setOnClickListener {
            if (!editText.text.toString().isNullOrBlank()) {
                findNavController().navigate(
                    SearchFragmentDirections.actionSearchFragmentToForecastFragment(
                        editText.text.toString()
                    )
                )
            }
        }
    }


}