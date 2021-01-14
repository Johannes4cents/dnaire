package com.example.dnaire.glide

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.dnaire.Main
import com.example.dnaire.fragments.LobbyFragment

lateinit var imageContainer: ImageView
val REQUEST_IMAGE_CAPTURE = 1
val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)


fun Fragment.takePic(picC: ImageView) {
    imageContainer = picC
    try {
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
    } catch (e: ActivityNotFoundException){
    }
}

class MediaHandler(): Fragment(){
    lateinit var d: LobbyFragment

    constructor(lf: LobbyFragment) : this() {
        d = lf
    }
    fun takePic(picC : ImageView) {
        imageContainer = picC
        try {
            d.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException){
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.i("testen", "mediahandler activity results called")
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == AppCompatActivity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            Glide.with(imageContainer.context)
                    .load(imageBitmap)
                    .into(imageContainer)
        }
    }
}


