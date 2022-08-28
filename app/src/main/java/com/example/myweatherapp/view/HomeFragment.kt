package com.example.myweatherapp.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.myweatherapp.R

class HomeFragment : Fragment() {
    private lateinit var homeFragmentView: View
    private lateinit var lookUpCityButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeFragmentView =
            LayoutInflater.from(context).inflate(R.layout.fragment_home, container, false)
        return homeFragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()

        lookUpCityButton = homeFragmentView.findViewById(R.id.lookUpCity_button)

        lookUpCityButton.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToSearchFragment()
            )
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        AndroidInjection.inject(this)
    }
}