package com.example.dnaire.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.dnaire.classes.Trait

@Dao
interface DaoTrait {
    @Delete
    fun delete(trait: Trait)

    @Query("SELECT * FROM Trait WHERE name = :name")
    fun get(name: String): Trait?

    @Update
    fun update(trait: Trait)

    @Insert()
    fun insert(trait: Trait)

    @Query("SELECT * FROM Trait ORDER BY name DESC")
    fun all(): List<Trait>

    @Query("SELECT * FROM Trait ORDER BY name DESC")
    fun allLive(): LiveData<List<Trait>?>

    @Query("SELECT * from Trait WHERE id= :id")
    fun getLive(id: Long): LiveData<Trait>?
}