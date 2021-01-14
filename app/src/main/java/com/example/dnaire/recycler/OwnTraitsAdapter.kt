package com.example.dnaire.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dnaire.classes.*
import com.example.dnaire.databinding.OwnTraitBinding
import com.example.dnaire.fragments.LobbyFragment

class OwnTraitsAdapter(val d: LobbyFragment): ListAdapter<OwnTraitFather, RecyclerView.ViewHolder>(OwnTraitDiff()) {
    fun give(traitList: List<OwnTrait>?){
        val toSubmitList = when(traitList) {
            null -> listOf(OwnTraitFather.OwnTraitHeader)
            else ->  traitList.map {
                OwnTraitFather.OwnTraitInAdapter(it)
            } + listOf(OwnTraitFather.OwnTraitHeader)
        }
        submitList(toSubmitList)
    }
    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is OwnTraitFather.OwnTraitInAdapter -> 0
            else -> 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(d.context)
        val traitBinding = OwnTraitBinding.inflate(inflater)
        return when(viewType){
            0 -> OwnTraitHolder(traitBinding, d)
            else -> OwnTraitHeader(traitBinding, d)
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
            is OwnTraitHolder -> {
                val trait = (getItem(position) as OwnTraitFather.OwnTraitInAdapter).trait
                holder.onBind(trait)}
            is OwnTraitHeader -> { holder.onBind()}
        }
    }
}


class OwnTraitDiff: DiffUtil.ItemCallback<OwnTraitFather>(){
    override fun areItemsTheSame(oldItem: OwnTraitFather, newItem: OwnTraitFather): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: OwnTraitFather, newItem: OwnTraitFather): Boolean {
        return oldItem == newItem
    }
}

sealed class OwnTraitFather() {
    abstract val name: String
    abstract val id: Long

    class OwnTraitInAdapter(val trait: OwnTrait): OwnTraitFather(){
        override val name : String
            get() = trait.name
        override val id: Long
            get() = trait.id
    }
    object OwnTraitHeader: OwnTraitFather(){
        override val name: String
            get() = "133333333333333333337"
        override val id: Long
            get() = 1888888888889999L
    }
}