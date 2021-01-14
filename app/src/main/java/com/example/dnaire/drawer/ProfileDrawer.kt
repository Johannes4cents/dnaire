package com.example.dnaire.drawer

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.dnaire.R
import com.example.dnaire.fragments.LobbyFragment
import com.example.dnaire.sections_profileDrawer.PicNameQuotes

class ProfileDrawer(context: Context, attrs: AttributeSet): androidx.constraintlayout.widget.ConstraintLayout(context, attrs) {
    lateinit var d: LobbyFragment
    lateinit var picNameQuotes : PicNameQuotes

    fun init(lf: LobbyFragment) {
        d = lf
        setViews()
        observer()
        clickListener()
        inits()
    }

    fun setViews(){
        picNameQuotes = findViewById(R.id.picNameQuotes)
    }

    fun observer(){

    }

    fun clickListener() {
    }

    fun inits(){
        picNameQuotes.init(d)
    }
}