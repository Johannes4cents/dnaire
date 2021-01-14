package com.example.dnaire.classes

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.dnaire.R
import com.example.dnaire.databinding.PopNewStrainBinding
import com.example.dnaire.databinding.PopNewTraitBinding
import com.example.dnaire.databinding.PopProfilePicBinding
import com.example.dnaire.fragments.LobbyFragment
import com.example.dnaire.glide.takePic
import kotlinx.coroutines.launch
//
class Pop(val d: LobbyFragment) {
    val inflater: LayoutInflater = LayoutInflater.from(d.context)
    private val popWindow = PopupWindow(d.context)
    private fun windowSetup(view: View, fromWhere: View) {
        popWindow.isOutsideTouchable = true
        popWindow.isFocusable = true
        popWindow.contentView = view
        popWindow.showAtLocation(fromWhere, Gravity.CENTER, 0 , -540)
        popWindow.animationStyle = R.style.pop_slide_up
        popWindow.setBackgroundDrawable(d.requireActivity().getDrawable(R.drawable.transparent))
    }
    fun newStrain(btn: View, strainViewThree: StrainView) {
        val popBinding = PopNewStrainBinding.inflate(inflater)
        windowSetup(popBinding.root, btn)
        popBinding.strainInput.requestFocus()
        d.imm.showSoftInput(popBinding.strainInput, 0)

        popBinding.okButton.setOnClickListener {
            val newStrain = Strain(popBinding.strainInput.text.toString())
            d.scope.launch {
                d.ao.strain.insert(newStrain)
                strainViewThree.strain.postValue(newStrain)
            }
            d.imm.hideSoftInputFromWindow(popBinding.strainInput.windowToken , 0)
            popWindow.dismiss()
        }
        popBinding.backButton.setOnClickListener {
            popWindow.dismiss()
            d.imm.hideSoftInputFromWindow(popBinding.strainInput.windowToken , 0)
        }
    }

    fun newTrait(btn: View){
        val inflater = LayoutInflater.from(d.context)
        val binding = PopNewTraitBinding.inflate(inflater)
        windowSetup(binding.root, btn)
        binding.traitInput.requestFocus()
        d.imm.showSoftInput(binding.traitInput, 0)
        binding.okButton.setOnClickListener {
            val newTrait = Trait(name = binding.traitInput.text.toString())
            if (newTrait.name.length > 2) d.scope.launch {d.ao.trait.insert(newTrait) }
            else Toast.makeText(btn.context, "Name is to short (min 3 charactes)", Toast.LENGTH_LONG).show()
            d.imm.hideSoftInputFromWindow(binding.traitInput.windowToken , 0)
            popWindow.dismiss()
        }
        binding.backButton.setOnClickListener {
            popWindow.dismiss()
            d.imm.hideSoftInputFromWindow(binding.traitInput.windowToken , 0)
        }
    }

    fun newOwnTrait(btn: View){
        val binding = PopNewTraitBinding.inflate(inflater)
        windowSetup(binding.root, btn)et
        binding.traitInput.requestFocus()
        d.imm.showSoftInput(binding.traitInput, 0)
        binding.okButton.setOnClickListener {
            val newOwnTrait = OwnTrait(name = binding.traitInput.text.toString())
            if (newOwnTrait.name.length > 2) d.scope.launch {d.ao.ownTrait.insert(newOwnTrait) }
            else Toast.makeText(btn.context, "Name is to short (min 3 charactes)", Toast.LENGTH_LONG).show()
            d.imm.hideSoftInputFromWindow(binding.traitInput.windowToken , 0)
            popWindow.dismiss()
        }
        binding.backButton.setOnClickListener {
            popWindow.dismiss()
            d.imm.hideSoftInputFromWindow(binding.traitInput.windowToken , 0)
        }
    }

    fun profilePic(btn: View, picContainer: ImageView) {
        val binding = PopProfilePicBinding.inflate(inflater)
        windowSetup(binding.root, btn)
        binding.btnUploadImage.setOnClickListener {
            //d.mHandler.takePic(picContainer)
        }
    }
}