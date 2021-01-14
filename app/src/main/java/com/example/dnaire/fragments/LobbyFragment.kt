package com.example.dnaire.fragments

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.dnaire.Main
import com.example.dnaire.R
import com.example.dnaire.classes.*
import com.example.dnaire.databinding.LobbyBinding
import com.example.dnaire.drawer.IpsDrawer
import com.example.dnaire.drawer.ProfileDrawer
import com.example.dnaire.glide.MediaHandler
import com.example.dnaire.room.NewRoom
import com.example.dnaire.sections.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LobbyFragment: Fragment() {
    companion object {

    }
    lateinit var imageContainer: ImageView
    lateinit var ao: Ao
    lateinit var scope: CoroutineScope
    lateinit var binding: LobbyBinding
    lateinit var imm: InputMethodManager
    // Drawer
    lateinit var ipsDrawer: IpsDrawer
    lateinit var profileDrawer: ProfileDrawer
    // PictureHandling
    //lateinit var mHandler: MediaHandler

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = LobbyBinding.inflate(layoutInflater)
        // Essentials
        essentials()

        // check if new Room DB
        checkNewRoom()
        // start Sections
        setViews()
        return binding.root
    }


    private fun setViews() {
        // BoredDating
        val boredDating = binding.master.findViewById<BoredDatingSwitch>(R.id.boredDating)
        boredDating.init()
        // DnairConst
        val dnairConst = binding.master.findViewById<DnairConst>(R.id.dnairConst)
        dnairConst.init(this)
        // BestOfFriends
        val bestOfFriends = binding.master.findViewById<BestOfFriends>(R.id.bestOfFriends)
        bestOfFriends.init()
        // Seconds
        val seconds = binding.master.findViewById<Seconds>(R.id.seconds)
        seconds.init()
        // Strains
        val strains = binding.master.findViewById<Strains>(R.id.strains)
        strains.init(this)
        // Traits
        val traits = binding.master.findViewById<Traits>(R.id.traits)
        traits.init(this)
        // Bottom
        val bottom = binding.master.findViewById<Bottom>(R.id.bottom)
        bottom.init(this)
        // Drawer
        val mainDrawer = binding.mainDrawer
        ipsDrawer = binding.mainDrawer.findViewById(R.id.ipsDrawer)
        profileDrawer = binding.mainDrawer.findViewById(R.id.profileDrawer)
        ipsDrawer.init()
        profileDrawer.init(this)
    }


    private fun essentials(){
        ao = Ao(this)
        val job = Job()
        scope = CoroutineScope(Dispatchers.IO + job)
        imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        //mHandler = MediaHandler(this)

    }

    private fun checkNewRoom(){
        scope.launch {
            ao.new.get("new")?.let {
                return@launch
            }
            ao.new.insert(NewRoom())
            getDesiredTraitsList(this@LobbyFragment)
            getOwnTraitsList(this@LobbyFragment)
            insertStrain(this@LobbyFragment)
        }
    }

    val REQUEST_IMAGE_CAPTURE = 1
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == AppCompatActivity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            Glide.with(imageContainer.context)
                    .load(imageBitmap)
                    .into(imageContainer)
        }

    }

}