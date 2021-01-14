package com.example.dnaire.sections_profileDrawer

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.dnaire.R
import com.example.dnaire.classes.Pop
import com.example.dnaire.fragments.LobbyFragment

class PicNameQuotes(context: Context, attrs: AttributeSet): androidx.constraintlayout.widget.ConstraintLayout(context, attrs) {
    lateinit var d : LobbyFragment
    lateinit var drawerProfilePic: ImageView
    lateinit var picContainer: ImageView
    lateinit var namePlate: ImageView
    lateinit var usernameText: TextView
    fun init(lf: LobbyFragment) {
        d = lf
        setViews()
        observer()
        clickListener()
    }

    fun setViews(){
        drawerProfilePic = findViewById(R.id.drawerProfilePic)
        picContainer = findViewById(R.id.picContainer)
        namePlate = findViewById(R.id.namePlate)
        usernameText = findViewById(R.id.usernameText)
    }

    fun observer(){

    }

    fun clickListener() {
        drawerProfilePic.setOnClickListener {
            Log.i("testen", "profileDrwaer/setviews - test")
            Pop(d).profilePic(this, picContainer)
        }
    }
}