package com.example.dnaire.sections

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.dnaire.R
import com.example.dnaire.fragments.BestOfFragmentDirections

class BestOfFriends(context: Context, attrs: AttributeSet): androidx.constraintlayout.widget.ConstraintLayout(context, attrs) {
    lateinit var btnBestOf : ImageView
    private lateinit var btnFriends: ImageView
    fun init() {
        setViews()
        observer()
        clickListener()
    }

    private fun setViews() {
        btnBestOf = findViewById(R.id.btnBestOf)
        btnFriends = findViewById(R.id.btnFriends)
    }

    private fun observer() {

    }

    private fun clickListener() {
        btnBestOf.setOnClickListener {
            findNavController().navigate(R.id.action_lobbyFragment_to_bestOfFragment)
        }

        btnFriends.setOnClickListener {
            findNavController().navigate(R.id.action_lobbyFragment_to_friendsFragment)
        }
    }
}