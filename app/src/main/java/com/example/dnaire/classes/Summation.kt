package com.example.dnaire.classes

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.dnaire.R
import com.example.dnaire.fragments.LobbyFragment
import com.example.dnaire.sections.Seconds

class Summation(context: Context, attrs: AttributeSet): androidx.constraintlayout.widget.ConstraintLayout(context, attrs) {
    fun manage() {
        val summationTextView = findViewById<TextView>(R.id.summationText)
        val secondsString = Seconds.seconds.value.toString()
        var summation = "Chat for $secondsString seconds about ${StrainView.chosenStrains}"
        var spanString = SpannableString(summation)
        spanString.setSpan(ForegroundColorSpan(Color.YELLOW), 9, 10, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
    }
}