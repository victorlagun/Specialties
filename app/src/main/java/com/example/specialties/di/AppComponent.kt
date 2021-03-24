package com.example.specialties.di

import android.app.Application
import com.example.specialties.SpecialtyApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ViewModelModule::class,
        MainActivityModule::class]
)
interface AppComponent : AndroidInjector<SpecialtyApp> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(specialtyApp: SpecialtyApp)
}
