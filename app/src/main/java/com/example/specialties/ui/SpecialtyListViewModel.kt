package com.example.specialties.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.specialties.model.Specialty
import com.example.specialties.repository.Repository
import com.example.specialties.util.addTo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SpecialtyListViewModel
@Inject constructor(private val repository: Repository) : ViewModel() {
    private val disposables = CompositeDisposable()
    fun getSpecialties(errorAction: () -> Unit): LiveData<List<Specialty>> {
        refresh(errorAction)
        return repository.getListOfSpecialties()
    }

    private fun refresh(errorAction: () -> Unit) {
        repository.refresh().observeOn(AndroidSchedulers.mainThread())
            .subscribe({}, {
                errorAction.invoke()
                it.printStackTrace()
            }).addTo(disposables)

    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}