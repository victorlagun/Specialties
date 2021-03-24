package com.example.specialties.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.specialties.model.Specialty

@Dao
interface SpecialtyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(specialty: List<Specialty>)

    @Query("SELECT * FROM specialty ORDER BY name DESC")
    fun getListOfSpecialty(): LiveData<List<Specialty>>
}