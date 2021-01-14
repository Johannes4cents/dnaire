package com.example.dnaire.sections

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dnaire.R
import com.example.dnaire.fragments.LobbyFragment
import com.example.dnaire.recycler.OwnTraitsAdapter
import com.example.dnaire.recycler.TraitsAdapter

class Traits(context: Context, attrs: AttributeSet): androidx.constraintlayout.widget.ConstraintLayout(context, attrs) {
    private lateinit var counterDesired : TextView
    private lateinit var counterOwn : TextView
    private lateinit var d: LobbyFragment
    private lateinit var desiredTraitsRec : RecyclerView
    private lateinit var ownTraitsRec: RecyclerView
    private lateinit var desiredTraits: TraitsAdapter
    private lateinit var ownTraits: OwnTraitsAdapter

    companion object {
        var selectedDesired = MutableLiveData<Int>(0)
        var selectedOwn = MutableLiveData<Int>(0)
    }
    fun init(lf: LobbyFragment) {
        d = lf
        setViews()
        setAdapter()
        observer()
    }

    private fun setViews() {
        counterDesired = findViewById(R.id.counterDesired)
        counterOwn = findViewById(R.id.counterOwn)
        desiredTraitsRec = findViewById(R.id.desiredTraitsRec)
        ownTraitsRec = findViewById(R.id.ownTraitsRec)
    }
    
    private fun observer() {
        selectedDesired.observe(context as LifecycleOwner, {
            counterDesired.text = it.toString()
            if(it > 3) {
                counterDesired.setTextColor(resources.getColor(R.color.green))
            }
            else{
                counterDesired.setTextColor(resources.getColor(R.color.white))
            }
        })

        selectedOwn.observe(context as LifecycleOwner, {
            counterOwn.text = it.toString()
            if(it > 3) {
                counterOwn.setTextColor(resources.getColor(R.color.yellow))
            }
            else{
                counterOwn.setTextColor(resources.getColor(R.color.white))
            }
        })

        d.ao.trait.allLive().observe(context as LifecycleOwner, {list->
            desiredTraits.give(list)
        })

        d.ao.ownTrait.allLive().observe(context as LifecycleOwner, {list ->
            ownTraits.give(list)
        })
    }

    private fun clickListener() {

    }

    private fun setAdapter() {
        desiredTraits = TraitsAdapter(d)
        desiredTraits.setHasStableIds(true)
        ownTraits = OwnTraitsAdapter(d)
        ownTraits.setHasStableIds(true)
        desiredTraitsRec.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        desiredTraitsRec.adapter = desiredTraits
        ownTraitsRec.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
        ownTraitsRec.adapter = ownTraits
    }
}
        

    


