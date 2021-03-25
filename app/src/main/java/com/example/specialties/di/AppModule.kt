package com.example.specialties.di

import android.app.Application
import androidx.room.Room
import com.example.specialties.api.Api
import com.example.specialties.api.BASE_URL
import com.example.specialties.db.*
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {
    @Singleton
    @Provides
    fun provideApi(): Api {
        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(client)
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

    @Singleton
    @Provides
    fun provideCrossRefDao(db: Database): CrossRefDao {
        return db.crossRefDao()
    }

//    @Singleton
//    @Provides
//    fun provideLastUpdateDao(db: Database): LastUpdateDao {
//        return db.lastUpdateDao()
//    }
}
