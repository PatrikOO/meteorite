package com.patrik.meteoritelandings.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalMeteorite::class], version = 1, exportSchema = false)
abstract class MeteoriteDatabase : RoomDatabase() {

    abstract fun meteoriteDao(): MeteoriteDao
}