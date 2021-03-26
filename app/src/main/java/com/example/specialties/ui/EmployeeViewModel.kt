package com.example.specialties.ui

import androidx.lifecycle.ViewModel
import com.example.specialties.repository.Repository
import javax.inject.Inject

class EmployeeViewModel
    @Inject constructor(private val repository: Repository): ViewModel() {
        fun getEmployee(employeeId: Int) = repository.getEmployee(employeeId)
        fun getSpecialties(employeeId: Int) = repository.getEmployeeSpecialty(employeeId)
    }