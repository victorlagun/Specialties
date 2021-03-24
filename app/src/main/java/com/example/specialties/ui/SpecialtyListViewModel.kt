package com.example.specialties.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.specialties.model.Specialty
import com.example.specialties.repository.Repository
import com.example.specialties.util.addTo
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SpecialtyListViewModel
@Inject constructor(private val repository: Repository) : ViewModel() {
    private val disposables = CompositeDisposable()
    fun getEmployees(): LiveData<List<Specialty>> {
        refresh()
        return repository.getListOfSpecialties()
    }

    fun refresh() {
        repository.refresh().subscribe({},{it.printStackTrace()}).addTo(disposables)

    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}