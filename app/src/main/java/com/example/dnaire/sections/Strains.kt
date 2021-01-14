package com.example.dnaire.sections

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import com.example.dnaire.R
import com.example.dnaire.classes.Pop
import com.example.dnaire.classes.StrainView
import com.example.dnaire.fragments.LobbyFragment

class Strains(context: Context, attrs: AttributeSet): androidx.constraintlayout.widget.ConstraintLayout(context, attrs) {
    companion object {
        var count = MutableLiveData<Int>(0)
    }
    lateinit var d : LobbyFragment
    private lateinit var strainViewOne: StrainView
    lateinit var strainViewTwo: StrainView
    lateinit var strainViewThree: StrainView
    lateinit var newStrain: ImageView
    lateinit var btnNewStrains: ImageView
    fun init(lf: LobbyFragment) {
        d = lf
        setViews()
        observer()
        clickListener()
        manage()
    }

    private fun setViews() {
        strainViewOne =findViewById<StrainView>(R.id.strainView1)
        strainViewTwo = findViewById<StrainView>(R.id.strainView2)
        strainViewThree = findViewById<StrainView>(R.id.strainView3)
        newStrain = findViewById(R.id.btnNewStrain)
        btnNewStrains = findViewById(R.id.btnNewStrains)

    }

    private fun observer() {

    }

    private fun clickListener() {
        newStrain.setOnClickListener {
            Pop(d).newStrain(it, strainViewThree)
        }
    }

    private fun manage() {
        strainViewOne.init(d, 1)
        strainViewTwo.init(d, 1)
        strainViewThree.init(d, 1)
    }
}