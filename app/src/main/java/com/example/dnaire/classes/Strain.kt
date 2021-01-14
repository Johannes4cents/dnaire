package com.example.dnaire.classes

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dnaire.R
import com.example.dnaire.fragments.LobbyFragment
import com.example.dnaire.sections.BoredDatingSwitch
import com.example.dnaire.sections.Strains
import kotlinx.coroutines.launch

@Entity
class Strain(
    @PrimaryKey
    @ColumnInfo val name: String,
    @ColumnInfo var selected: Boolean = false,
    @ColumnInfo var chosenTotal: Int = 0,
    @ColumnInfo var dismissedTotal: Int = 0,
    @ColumnInfo var chosen: Boolean = false,
    @ColumnInfo var dismissed: Boolean = false,
    @ColumnInfo var favorite: Boolean = false,
)
{
}
fun insertStrain(d: LobbyFragment) {
    var strainOne = Strain("Trump")
    var strainTwo = Strain("The Moon")
    var strainThree = Strain("Motorcycles")
    var strainFour = Strain("bad breakup")
    var strainFive = Strain("The weather")
    var strainSix = Strain("Being gay")
    var strainSeven = Strain("nudes")
    var strainEight = Strain("Joking")
    var strainNine = Strain("Yelling")
    var strainTen = Strain("confession")
    var strain11 = Strain("Most embarassing moment")
    var strain12 = Strain("I'm always right. prove me wrong")
    var strain13 = Strain("For how much money would you kill a man?")
    var strain14 = Strain("try not to laugh challenge")
    var strain15 = Strain("my weirdest face")
    var strain16 = Strain("watch me dance")

    d.scope.launch {
        val strainList = listOf(strainEight,strainFive,strainFour,strainNine,strainOne,strainSeven,strainSix,strainTen,strainThree,strainTwo, strain11, strain12, strain13, strain14,strain15,strain16)
        strainList.forEach {
            d.ao.strain.insert(it)
        }
    }
}

class StrainView(context: Context, attrs: AttributeSet): androidx.constraintlayout.widget.ConstraintLayout(context, attrs) {
    var strain = MutableLiveData<Strain>()
    var imSelected = MutableLiveData<Boolean>(false)
    lateinit var strainPic: ImageView
    lateinit var strainText: TextView
    companion object {
        var chosenStrains = mutableSetOf<String?>(
        )
    }


    fun init(d: LobbyFragment, pos: Int){
        strainPic = findViewById<ImageView>(R.id.strainImage)
        strainText = findViewById<TextView>(R.id.strainText)

        d.scope.launch {
            strain.postValue(d.ao.strain.all().random())
        }
        obsSelected(d)
        clickListener(d)
    }

    private fun obsSelected(d: LobbyFragment) {
        var selectedResource = Color.GREEN

        BoredDatingSwitch.bored.observe(context as LifecycleOwner, {
            selectedResource = if(it) Color.GREEN else Color.MAGENTA
            if(imSelected.value!!) strainText.setTextColor(selectedResource)
        })
        strain.observe(context as LifecycleOwner, {
            strainText.text = it?.name

        })
        imSelected.observe(context as LifecycleOwner, {
            if (it) {
                strainText.setTextColor(selectedResource); chosenStrains.add(strain.value!!.name); Strains.count.value = Strains.count.value!! + 1
            } else strainText.setTextColor(Color.WHITE); chosenStrains.remove(strain.value?.name); Strains.count.value = Strains.count.value!! - 1
        })


    }

    private fun clickListener(d: LobbyFragment) {
        strainPic.setOnClickListener {
            imSelected.value = !imSelected.value!!
        }

    }
}
