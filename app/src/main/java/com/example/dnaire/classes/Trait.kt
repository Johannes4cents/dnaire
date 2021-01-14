package com.example.dnaire.classes

import android.graphics.Color
import android.util.Log
import androidx.compose.ui.graphics.BlendMode
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dnaire.R
import com.example.dnaire.databinding.TraitBinding
import com.example.dnaire.fragments.LobbyFragment
import com.example.dnaire.sections.BoredDatingSwitch
import com.example.dnaire.sections.Traits
import kotlinx.coroutines.launch

@Entity
class Trait(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    var id: Long = 0L,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    var chosen: Int = 0,
    @ColumnInfo
    var selected: Boolean = false,
    @ColumnInfo
    var isNew: Boolean = true,
)

class TraitHolder(val binding: TraitBinding, val d: LobbyFragment): RecyclerView.ViewHolder(binding.root){
    fun onBind(trait: Trait) {
        binding.trait = trait
        var selectedResource = Color.GREEN
        BoredDatingSwitch.bored.observe(itemView.context as LifecycleOwner, {bool ->
            if(bool){
                selectedResource = Color.GREEN
                if(trait.selected) binding.traitName.setTextColor(selectedResource)
            }
            else {
                selectedResource = Color.MAGENTA
                if(trait.selected) binding.traitName.setTextColor(selectedResource)
            }
        })
        d.ao.trait.getLive(trait.id)?.observe(itemView.context as LifecycleOwner, {
            if(it.selected)binding.traitName.setTextColor(selectedResource)
            else binding.traitName.setTextColor(Color.WHITE)
        })
        binding.traitPic.setOnClickListener {
            if(Traits.selectedDesired.value!! < 2) {
                if(trait.selected) {
                    Traits.selectedDesired.value = Traits.selectedDesired.value!! - 1
                    trait.selected = false
                }
                else {
                    Traits.selectedDesired.value = Traits.selectedDesired.value!! + 1
                    trait.selected = true
                }
                d.scope.launch {
                    d.ao.trait.update(trait)
                }
            }
            else {
                if(trait.selected) {
                    trait.selected = false
                    Traits.selectedDesired.value = Traits.selectedDesired.value!! - 1
                    d.scope.launch {
                        d.ao.trait.update(trait)
                    }
                }
            }
        }
    }
}

fun getDesiredTraitsList(d: LobbyFragment) {
    val traitOne = Trait(name = "Nice")
    val traiTwo = Trait(name ="Funny")
    val traitThree = Trait(name ="Angry")
    val traitFour = Trait(name ="Sweet")
    val traitFive = Trait(name ="Emphatic")
    val traitSix = Trait(name ="Horny")
    val traitSeven = Trait(name ="Hot")
    val traitEight = Trait(name ="Liberal")
    val traitNine = Trait(name ="Conservative")
    d.scope.launch {
        listOf(traiTwo, traitEight, traitFive, traitFour, traitNine, traitOne, traitSeven, traitSix, traitThree).forEach {
            d.ao.trait.insert(it)
        }
    }
}
fun getOwnTraitsList(d: LobbyFragment) {
    val traitOne = OwnTrait(name = "Nice")
    val traiTwo = OwnTrait(name = "Funny")
    val traitThree = OwnTrait(name = "Angry")
    val traitFour = OwnTrait(name = "Sweet")
    val traitFive = OwnTrait(name = "Emphatic")
    val traitSix = OwnTrait(name = "Horny")
    val traitSeven = OwnTrait(name = "Hot")
    val traitEight = OwnTrait(name = "Liberal")
    val traitNine = OwnTrait(name = "Conservative")
    d.scope.launch {
        listOf(traiTwo, traitEight, traitFive, traitFour, traitNine, traitOne, traitSeven, traitSix, traitThree).forEach {
            d.ao.ownTrait.insert(it)
        }
    }
}



