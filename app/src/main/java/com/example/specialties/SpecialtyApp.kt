package com.example.specialties

import android.app.Application
import com.example.specialties.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.reactivex.plugins.RxJavaPlugins
import javax.inject.Inject


class SpecialtyApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        DaggerAppComponent.builder().application(this)
            .build().inject(this)
        RxJavaPlugins.setErrorHandler { it.printStackTrace() }
    }

    override fun androidInjector() = dispatchingAndroidInjector
}
