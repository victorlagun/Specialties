package com.example.specialties.api

import com.example.specialties.model.SpecialityDataDto
import io.reactivex.Observable
import retrofit2.http.GET

const val BASE_URL = "https://gitlab.65apps.com/65gb/static/raw/master"

interface Api {

    @GET("/testTask.json")
    fun getEmployees(): Observable<SpecialityDataDto>
}