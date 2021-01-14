package com.example.dnaire.classes

import android.graphics.Color
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dnaire.R
import com.example.dnaire.databinding.OwnTraitBinding
import com.example.dnaire.databinding.TraitBinding
import com.example.dnaire.fragments.LobbyFragment
import com.example.dnaire.sections.BoredDatingSwitch
import com.example.dnaire.sections.Traits
import kotlinx.coroutines.launch

@Entity
class OwnTrait(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var id: Long = 0L,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    var rating: Long = 0L,
    @ColumnInfo
    var chosen: Int = 0,
    @ColumnInfo
    var selected: Boolean = false,
    @ColumnInfo
    var isNew: Boolean = true,
)

class OwnTraitHolder(val binding: OwnTraitBinding, val d: LobbyFragment): RecyclerView.ViewHolder(binding.root){
    fun onBind(trait: OwnTrait) {
        binding.trait = trait
        var selectedResource = Color.GREEN
        BoredDatingSwitch.bored.observe(itemView.context as LifecycleOwner, {bool ->
            selectedResource = if(bool) Color.GREEN else Color.MAGENTA
            if(trait.selected) binding.traitName.setTextColor(selectedResource)
        })

        d.ao.trait.getLive(trait.id)?.observe(itemView.context as LifecycleOwner, {
            binding.traitName.setTextColor(if(it.selected) selectedResource else Color.WHITE)
        })

        binding.traitPic.setOnClickListener {
            if(Traits.selectedOwn.value!! < 2) {
                if(trait.selected) {
                    Traits.selectedOwn.value = Traits.selectedOwn.value!! - 1
                    trait.selected = false
                }
                else {
                    Traits.selectedOwn.value = Traits.selectedOwn.value!! + 1
                    trait.selected = true
                }
                d.scope.launch {
                    d.ao.ownTrait.update(trait as OwnTrait)
                }
            }
            else {
                if(trait.selected) {
                    trait.selected = false
                    Traits.selectedOwn.value = Traits.selectedOwn.value!! - 1
                    d.scope.launch {
                        d.ao.ownTrait.update(trait)
                    }
                }
            }
        }
    }
}