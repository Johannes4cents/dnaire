package com.example.dnaire.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.dnaire.classes.OwnTrait

@Dao
interface DaoOwnTrait {
    @Delete
    fun delete(trait: OwnTrait)

    @Query("SELECT * FROM OwnTrait WHERE name = :name")
    fun get(name: String): OwnTrait?

    @Update
    fun update(trait: OwnTrait)

    @Insert()
    fun insert(trait: OwnTrait)

    @Query("SELECT * FROM OwnTrait ORDER BY name DESC")
    fun all(): List<OwnTrait>

    @Query("SELECT * FROM OwnTrait ORDER BY name DESC")
    fun allLive(): LiveData<List<OwnTrait>?>

    @Query("SELECT * from OwnTrait WHERE id= :id")
    fun getLive(id: Long): LiveData<OwnTrait>?
}