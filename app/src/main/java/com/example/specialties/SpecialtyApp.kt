package com.example.specialties

import android.app.Application
import com.example.specialties.di.DaggerAppComponent


class SpecialtyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder().application(this)
            .build().inject(this)
    }
}
