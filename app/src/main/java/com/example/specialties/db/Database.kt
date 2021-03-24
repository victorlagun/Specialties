package com.example.specialties.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.specialties.model.Employee
import com.example.specialties.model.EmployeeSpecialtyCrossRef
import com.example.specialties.model.Specialty

const val DB_NAME = "specialties.db"

@Database(
    entities = [
        Employee::class, Specialty::class, EmployeeSpecialtyCrossRef::class],
    version = 1,
    exportSchema = true
)
abstract class Database : RoomDatabase() {

    abstract fun employeeDao(): EmployeeDao

    abstract fun specialtyDao(): SpecialtyDao

    abstract fun crossRefDao(): CrossRefDao
}
