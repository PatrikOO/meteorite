package com.patrik.meteorite.data.source.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface MeteoriteDao {

    @Query("SELECT * FROM meteorite")
    fun observeAll(): List<LocalMeteorite>

    @Upsert
    suspend fun upsertAll(meteorites: List<LocalMeteorite>)

    @Query("DELETE FROM meteorite")
    suspend fun deleteAll()
}