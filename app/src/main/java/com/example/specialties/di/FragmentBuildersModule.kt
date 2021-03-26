package com.example.specialties.di

import com.example.specialties.ui.EmployeeFragment
import com.example.specialties.ui.EmployeeListFragment
import com.example.specialties.ui.SpecialtyListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeRepoFragment(): SpecialtyListFragment

    @ContributesAndroidInjector
    abstract fun contributeUserFragment(): EmployeeListFragment

    @ContributesAndroidInjector
    abstract fun contributeSearchFragment(): EmployeeFragment
}
