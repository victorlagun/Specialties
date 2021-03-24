package com.example.specialties.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.specialties.model.Employee
import com.example.specialties.model.Specialty

@Database(
    entities = [
        Employee::class, Specialty::class],
    version = 0,
    exportSchema = true
)
abstract class Database : RoomDatabase() {

    abstract fun employeeDao(): EmployeeDao

    abstract fun specialtyDao(): SpecialtyDao
}
