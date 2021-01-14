package com.example.dnaire.firebase

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class FireUser(
    var username: String? = "",
    var agoraUid: String? = ""
)