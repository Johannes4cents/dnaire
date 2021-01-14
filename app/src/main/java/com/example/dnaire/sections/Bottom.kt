package com.example.dnaire.sections

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import com.example.dnaire.R
import com.example.dnaire.camera.AgoraVideoChat
import com.example.dnaire.classes.StrainView
import com.example.dnaire.classes.Summation
import com.example.dnaire.fragments.LobbyFragment

class Bottom(context: Context, attrs: AttributeSet): androidx.constraintlayout.widget.ConstraintLayout(context, attrs) {
    lateinit var btnGo: ImageView
    lateinit var btnVid: ImageView
    lateinit var d : LobbyFragment
    lateinit var summation: Summation
    fun init(lobbyF: LobbyFragment) {
        d = lobbyF
        setViews()
        observer()
        clickListener()

        summation.manage()
    }

    private fun setViews(){
        summation = findViewById(R.id.summation)
        btnGo = findViewById(R.id.btnGo)
        btnVid = findViewById(R.id.btn_video)
    }

    private fun observer(){
        Strains.count.observe(context as LifecycleOwner, {
            Log.i("testen", "bottom/observer - counter is $it")
            if(it <= 0) {
                btnGo.setImageResource(R.drawable.btn_go_grey)
                btnVid.setImageResource(R.drawable.btn_upload_video_grey)
            }
            else {
                btnGo.setImageResource(R.drawable.btn_go_green)
                btnVid.setImageResource(R.drawable.btn_upload_video)
            }
        })
    }

    private fun clickListener(){
        btnGo.setOnClickListener {
            val intent = Intent(this.context, AgoraVideoChat::class.java)
            intent.putExtra("userName", "SeriAce")
            d.startActivity(intent)

        }
    }
}