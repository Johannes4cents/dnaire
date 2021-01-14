package com.example.dnaire.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dnaire.classes.*
import com.example.dnaire.databinding.TraitBinding
import com.example.dnaire.fragments.LobbyFragment

class TraitsAdapter(val d: LobbyFragment): ListAdapter<TraitFather, RecyclerView.ViewHolder>(TraitDiff()) {
    fun give(traitList: List<Trait>?){
        val toSubmitList = when(traitList) {
            null -> listOf(TraitFather.TraitHeader)
            else ->  traitList.map {
                TraitFather.TraitInAdapter(it)
            } + listOf(TraitFather.TraitHeader)
        }
        submitList(toSubmitList)
    }
    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is TraitFather.TraitInAdapter -> 0
            else -> 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(d.context)
        val traitBinding = TraitBinding.inflate(inflater)
        return when(viewType){
            0 -> TraitHolder(traitBinding, d)
            else -> TraitHeader(traitBinding, d)
        }
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(hasStableIds)
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).id
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder) {
            is TraitHolder -> {
                val trait = (getItem(position) as TraitFather.TraitInAdapter).trait
                holder.onBind(trait)}
            is TraitHeader -> { holder.onBind()}
        }
    }
}


class TraitDiff: DiffUtil.ItemCallback<TraitFather>(){
    override fun areItemsTheSame(oldItem: TraitFather, newItem: TraitFather): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TraitFather, newItem: TraitFather): Boolean {
        return oldItem == newItem
    }
}

sealed class TraitFather() {
    abstract val name: String
    abstract val id: Long

    class TraitInAdapter(val trait: Trait): TraitFather(){
        override val name : String
            get() = trait.name
        override val id: Long
            get() = trait.id
    }
    object TraitHeader: TraitFather(){
        override val name: String
            get() = "133333333333333333337"
        override val id: Long
            get() = 1888888888889999L
    }
}