package com.example.dnaire.classes

import androidx.recyclerview.widget.RecyclerView
import com.example.dnaire.R
import com.example.dnaire.databinding.OwnTraitBinding
import com.example.dnaire.databinding.TraitBinding
import com.example.dnaire.fragments.LobbyFragment

class TraitHeader(val binding: TraitBinding, val d: LobbyFragment): RecyclerView.ViewHolder(binding.root) {
    fun onBind(){
        binding.traitPic.setImageResource(R.drawable.trait_header)
        clickListeners()
    }
    private fun clickListeners() {
        binding.traitPic.setOnClickListener {
            Pop(d).newTrait(it)
        }
    }
}

class OwnTraitHeader(val binding: OwnTraitBinding, val d: LobbyFragment): RecyclerView.ViewHolder(binding.root) {
    fun onBind(){
        binding.traitPic.setImageResource(R.drawable.trait_header)
        clickListeners()
    }
    private fun clickListeners() {
        binding.traitPic.setOnClickListener {
            Pop(d).newOwnTrait(it)
        }
    }
}