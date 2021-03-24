package com.example.specialties.model

import androidx.room.Entity

@Entity(tableName = "employee")
data class Employee(
    val avatr_url: String?,
    val birthday: String?,
    val f_name: String?,
    val l_name: String?,
    val specialty: List<Specialty>?
)

@Entity(tableName = "specialty")
data class Specialty(
    val name: String?,
    val specialty_id: Int?
)