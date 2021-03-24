package com.example.specialties

import android.app.Application
import com.example.specialties.di.DaggerAppComponent
import io.reactivex.plugins.RxJavaPlugins


class SpecialtyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        init()

    }

    private fun init() {
        DaggerAppComponent.builder().application(this)
            .build().inject(this)
        RxJavaPlugins.setErrorHandler { it.printStackTrace() }
    }
}
