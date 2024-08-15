package com.patrik.meteoritelandings.di

import android.content.Context
import androidx.room.Room
import com.patrik.meteoritelandings.data.source.local.MeteoriteDao
import com.patrik.meteoritelandings.data.source.local.MeteoriteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): MeteoriteDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MeteoriteDatabase::class.java,
            "Meteorite.db"
        ).build()
    }

    @Provides
    fun provideTaskDao(database: MeteoriteDatabase): MeteoriteDao = database.meteoriteDao()
}
