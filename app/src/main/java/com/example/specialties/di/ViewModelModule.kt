package com.example.specialties.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.specialties.ui.EmployeeListViewModel
import com.example.specialties.ui.EmployeeViewModel
import com.example.specialties.ui.SpecialtyListViewModel
import com.example.specialties.util.SpecialtyViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SpecialtyListViewModel::class)
    abstract fun bindSpecialtyViewModel(viewModel: SpecialtyListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EmployeeListViewModel::class)
    abstract fun bindEmployeeListViewModel(viewModel: EmployeeListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EmployeeViewModel::class)
    abstract fun bindEmployeeViewModel(viewModel: EmployeeViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: SpecialtyViewModelFactory): ViewModelProvider.Factory
}
