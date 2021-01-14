package com.example.dnaire.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dnaire.R
import com.example.dnaire.databinding.FragmentStartBinding

class StartFragment : Fragment() {
    lateinit var binding : FragmentStartBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(inflater)
        clickListeners()
        return binding.root
    }


    private fun clickListeners() {
        binding.registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_signUpFragment)
        }
        binding.logInButton.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_lobbyFragment)
        }
    }

}