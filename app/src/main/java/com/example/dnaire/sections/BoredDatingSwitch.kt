package com.example.dnaire.sections

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.example.dnaire.R

class BoredDatingSwitch(context: Context, attrs: AttributeSet): androidx.constraintlayout.widget.ConstraintLayout(context, attrs) {
    lateinit var btnBored : ImageView
    lateinit var btnNearMe: ImageView
    companion object {
        var bored = MutableLiveData<Boolean>(true)
    }
    fun init() {
        setViews()
        observer()
        clickListener()
    }

    private fun setViews() {
        btnBored = findViewById(R.id.btnBored)
        btnNearMe = findViewById(R.id.btnNearMe)
    }

    private fun observer() {
        bored.observe(context as LifecycleOwner, {
            if(it) {
                btnBored.setImageResource(R.drawable.btn_bored_selected)
                btnNearMe.setImageResource(R.drawable.btn_near_me_unselected)
            }
            else{
                btnNearMe.setImageResource(R.drawable.btn_near_me_selected)
                btnBored.setImageResource(R.drawable.bored_box_unselected)
            }
        })
    }

    private fun clickListener() {
        btnBored.setOnClickListener {
            bored.value = true
        }
        btnNearMe.setOnClickListener {
            bored.value = false
        }
    }
}