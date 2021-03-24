package com.example.specialties.ui

import androidx.lifecycle.ViewModel
import com.example.specialties.repository.Repository
import com.example.specialties.util.addTo
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class EmployeeListViewModel
@Inject constructor(private val repository: Repository) : ViewModel() {
    private val disposables = CompositeDisposable()
    fun getEmployees() {
        repository.getListOfSpecialties()
    }

    fun refresh() {
        repository.refresh().subscribe({},{it.printStackTrace()}).addTo(disposables)

    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}