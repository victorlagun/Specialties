package com.example.specialties.model

import androidx.room.*

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

@Entity(primaryKeys = ["employeeId", "specialtyId"])
data class EmployeeSpecialtyCrossRef(
    val employeeId: Int,
    val specialtyId: Int
)

data class EmployeeWithSpecialty(
    @Embedded val employee: Employee,
    @Relation(
        parentColumn = "employee_id",
        entityColumn = "specialty_id",
        associateBy = Junction(EmployeeSpecialtyCrossRef::class)
    )
    val specialty: List<Specialty>?
)