package com.example.dnaire.sections

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.example.dnaire.R

class Seconds(context: Context, attrs: AttributeSet): androidx.constraintlayout.widget.ConstraintLayout(context, attrs) {
    companion object {
        var seconds = MutableLiveData(15)
    }
    private lateinit var btn15s : ImageView
    private lateinit var btn30s : ImageView
    private lateinit var btn60s : ImageView
    private lateinit var s15: TextView
    private lateinit var s30: TextView
    private lateinit var s60: TextView
    fun init() {
        setViews()
        clickListener()
        observer()
    }
    
    fun setViews() {
        btn15s = findViewById(R.id.btn15s)
        btn30s = findViewById(R.id.btn30s)
        btn60s = findViewById(R.id.btn60s)
        s15 = findViewById(R.id.s15)
        s30 = findViewById(R.id.s30)
        s60 = findViewById(R.id.s60)
    }
    
    private fun clickListener() {
        btn15s.setOnClickListener {
            seconds.value = 15
        }
        btn30s.setOnClickListener {
            seconds.value = 30
        }
        btn60s.setOnClickListener {
            seconds.value = 60
        }
    }
    var selectedResource = R.color.green
    private fun observer() {
        seconds.observe(this.context as LifecycleOwner, { secs ->
            BoredDatingSwitch.bored.observe(context as LifecycleOwner, {
                selectedResource = if (it) R.color.green else R.color.pink
                setSeconds(secs)
            })
            setSeconds(secs)
        })
    }

    private  fun setSeconds(secs: Int) {
        when(secs) {
            15 -> {
                s15.setTextColor(resources.getColor(selectedResource))
                s30.setTextColor(resources.getColor(R.color.white))
                s60.setTextColor(resources.getColor(R.color.white))
            }
            30 -> {
                s15.setTextColor(resources.getColor(R.color.white))
                s30.setTextColor(resources.getColor(selectedResource))
                s60.setTextColor(resources.getColor(R.color.white))
            }
            60 -> {
                s15.setTextColor(resources.getColor(R.color.white))
                s30.setTextColor(resources.getColor(R.color.white))
                s60.setTextColor(resources.getColor(selectedResource))
            }
        }
    }
}
