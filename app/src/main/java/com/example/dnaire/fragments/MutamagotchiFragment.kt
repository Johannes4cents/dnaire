package com.example.dnaire.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.dnaire.R
import com.example.dnaire.databinding.MutamagotchiFragmentBinding

class MutamagotchiFragment: Fragment() {
    lateinit var binding: MutamagotchiFragmentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = MutamagotchiFragmentBinding.inflate(inflater)
        setViews()
        observer()
        clickListener()
        return binding.root
    }

    fun setViews(){
        binding.dnairConst.settings.setImageResource(R.drawable.back_button_big)
        binding.dnairConst.profilePicContainer.setImageResource(R.drawable.mutama_stand_in)
    }

    fun observer(){

    }

    fun clickListener() {
        binding.dnairConst.settings.setOnClickListener {
            findNavController().navigate(R.id.action_mutamagotchiFragment_to_lobbyFragment)
        }
    }
}