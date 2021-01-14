package com.example.dnaire.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dnaire.databinding.BestOfFragmentBinding

class BestOfFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = BestOfFragmentBinding.inflate(inflater)
        setViews()
        observer()
        clickListener()
        return binding.root
    }

    fun setViews(){

    }

    fun observer(){

    }

    fun clickListener() {

    }
}
