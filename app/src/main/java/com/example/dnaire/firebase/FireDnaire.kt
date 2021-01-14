package com.example.dnaire.firebase

import com.example.dnaire.camera.AgoraVideoChat
import com.google.firebase.database.FirebaseDatabase

fun AgoraVideoChat.firebase() {
    val userName = this.intent.extras?.getString("userName")
    val mRef = FirebaseDatabase.getInstance().getReference("Users")
    mRef.push()
    mRef.child("Users").setValue(FireUser(userName, "test"))

}