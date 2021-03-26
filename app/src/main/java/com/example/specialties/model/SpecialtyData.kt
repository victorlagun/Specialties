package com.example.specialties.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee")
data class Employee(
    @PrimaryKey
    val employee_id: Int,
    val avatr_url: String?,
    val birthday: String?,
    val f_name: String?,
    val l_name: String?
)

@Entity(tableName = "specialty")
data class Specialty(
    val name: String,
    @PrimaryKey
    val specialty_id: Int
)

@Entity(tableName = "employee_specialty_cross_ref", primaryKeys = ["employee_id", "specialty_id"])
data class EmployeeSpecialtyCrossRef(
    val employee_id: Int,
    val specialty_id: Int
)