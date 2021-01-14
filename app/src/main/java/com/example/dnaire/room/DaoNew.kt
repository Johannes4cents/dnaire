package com.example.dnaire.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DaoNew {
    @Query("SELECT * from NewRoom where name= :name")
    fun get(name: String): NewRoom?
    @Insert
    fun insert(newRoom: NewRoom)
    @Update
    fun update(newRoom: NewRoom)
}