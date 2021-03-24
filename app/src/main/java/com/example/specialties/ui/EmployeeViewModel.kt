package com.example.specialties.ui

import androidx.lifecycle.ViewModel
import com.example.specialties.repository.Repository
import javax.inject.Inject

class EmployeeViewModel
    @Inject constructor(repository: Repository): ViewModel()