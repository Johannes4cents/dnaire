package com.example.dnaire.sections

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import com.example.dnaire.R
import com.example.dnaire.fragments.LobbyFragment

class DnairConst(context: Context, attrs: AttributeSet): androidx.constraintlayout.widget.ConstraintLayout(context, attrs) {
    lateinit var d : LobbyFragment
    lateinit var dnairLogo: ImageView
    lateinit var btnSettings: ImageView
    lateinit var btnProfile: ImageView
    fun init(lf : LobbyFragment) {
        d = lf
        setViews()
        observer()
        clickListener()
    }

    private fun setViews() {
        dnairLogo = findViewById(R.id.dnairLogo)
        btnSettings = findViewById(R.id.settings)
        btnProfile = findViewById(R.id.profilePic)
    }

    private fun observer() {
        BoredDatingSwitch.bored.observe(context as LifecycleOwner, {
            if(it) {
                dnairLogo.setImageResource(R.drawable.dnair_logo)
            }
            else {
                dnairLogo.setImageResource(R.drawable.dnair_logo_red)
            }
        })
    }

    private fun clickListener() {
        btnSettings.setOnClickListener { d.binding.mainDrawer.openDrawer(d.ipsDrawer) }
        btnProfile.setOnClickListener { d.binding.mainDrawer.openDrawer(d.profileDrawer) }
        dnairLogo.setOnClickListener { findNavController().navigate(R.id.action_lobbyFragment_to_mutamagotchiFragment) }
    }
}