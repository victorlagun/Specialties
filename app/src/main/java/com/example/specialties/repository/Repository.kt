package com.example.specialties.repository

import com.example.specialties.api.Api
import com.example.specialties.db.CrossRefDao
import com.example.specialties.db.EmployeeDao
import com.example.specialties.db.SpecialtyDao
import com.example.specialties.model.SpecialtyDataDto
import com.example.specialties.model.toEmployee
import com.example.specialties.model.toEmployeeSpecialtyCrossRef
import com.example.specialties.model.toSpecialty
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class Repository
@Inject constructor(
    private val api: Api,
    private val employeeDao: EmployeeDao,
    private val specialtyDao: SpecialtyDao,
    private val crossRefDao: CrossRefDao
) {
    fun getListOfSpecialties() = specialtyDao.getListOfSpecialty()
    fun getListOfEmployees(specialtyId: Int) = employeeDao.getListOfEmployee(specialtyId)
    fun getEmployee(employeeId: Int) = employeeDao.getEmployee(employeeId)

    fun refresh(): Observable<Unit> =
        api.getEmployees()
            .flatMap { fillDatabase(it) }.subscribeOn(Schedulers.io())

    private fun fillDatabase(specialityDataDto: SpecialtyDataDto) = Observable.fromCallable {
        employeeDao.insert(specialityDataDto.response.map { it.toEmployee() })
        specialtyDao.insert(specialityDataDto.response.map { response -> response.specialty.map { it.toSpecialty() } }
            .flatten())
        crossRefDao.insert(specialityDataDto.toEmployeeSpecialtyCrossRef())
    }
}