package com.example.specialties.di

import android.app.Application
import androidx.room.Room
import com.example.specialties.api.Api
import com.example.specialties.api.BASE_URL
import com.example.specialties.db.DB_NAME
import com.example.specialties.db.Database
import com.example.specialties.db.EmployeeDao
import com.example.specialties.db.SpecialtyDao
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideApi(): Api {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    @Singleton
    @Provides
    fun provideDb(app: Application): Database {
        return Room
            .databaseBuilder(app, Database::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideEmployeeDao(db: Database): EmployeeDao {
        return db.employeeDao()
    }

    @Singleton
    @Provides
    fun provideSpecialtyDao(db: Database): SpecialtyDao {
        return db.specialtyDao()
    }
}
