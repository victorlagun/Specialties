package com.example.specialties.ui

import androidx.lifecycle.ViewModel
import com.example.specialties.repository.Repository
import javax.inject.Inject

class EmployeeListViewModel
@Inject constructor(private val repository: Repository) : ViewModel() {

    fun getEmployees(specialtyId: Int) = repository.getListOfEmployees(specialtyId)

}