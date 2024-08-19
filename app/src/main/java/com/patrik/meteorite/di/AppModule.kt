package com.patrik.meteorite.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.patrik.meteorite.AppConfig
import com.patrik.meteorite.api.ApiService
import com.patrik.meteorite.data.source.local.MeteoriteDao
import com.patrik.meteorite.data.source.local.MeteoriteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): MeteoriteDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MeteoriteDatabase::class.java,
            "Meteorite.db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideMeteoriteDao(database: MeteoriteDatabase): MeteoriteDao = database.meteoriteDao()

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun providesFusedLocationProviderClient(
        application: Application
    ): FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(application)

//    @Provides
//    @Singleton
//    fun providesLocationTracker(
//        fusedLocationProviderClient: FusedLocationProviderClient,
//        application: Application
//    ): LocationTracker = DefaultLocationTracker(
//        fusedLocationProviderClient = fusedLocationProviderClient,
//        application = application
//    )
}
