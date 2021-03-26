package com.example.specialties.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.specialties.model.EmployeeSpecialtyCrossRef

@Dao
interface CrossRefDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(crossRef: List<EmployeeSpecialtyCrossRef>)
}