package com.example.dnaire.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.dnaire.classes.Strain

@Dao
interface DaoStrain {
    @Delete
    fun delete(strain: Strain)

    @Query("SELECT * FROM Strain WHERE name = :name")
    fun get(name: String): Strain?

    @Update
    fun update(strain: Strain)

    @Insert()
    fun insert(strain: Strain)

    @Query("SELECT * FROM Strain ORDER BY name DESC")
    fun all(): List<Strain>

    @Query("SELECT * FROM Strain ORDER BY name DESC")
    fun allLive(): LiveData<List<Strain>?>

    @Query("SELECT * from Strain WHERE name= :name")
    fun getLive(name: String?): LiveData<Strain>?
}