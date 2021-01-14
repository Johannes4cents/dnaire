package com.example.dnaire.classes

import com.example.dnaire.fragments.LobbyFragment
import com.example.dnaire.room.RoomDb

class Ao(lobbyFragment: LobbyFragment) {
    val trait = RoomDb.getInstance(lobbyFragment.requireActivity().application).daoTrait
    val ownTrait = RoomDb.getInstance(lobbyFragment.requireActivity().application).daoOwnTrait
    val strain = RoomDb.getInstance(lobbyFragment.requireActivity().application).daoStrain
    val new = RoomDb.getInstance(lobbyFragment.requireActivity().application).daoNew

}