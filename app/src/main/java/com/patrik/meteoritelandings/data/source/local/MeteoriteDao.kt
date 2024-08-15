package com.patrik.meteoritelandings.data.source.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface MeteoriteDao {

    @Query("SELECT * FROM meteorite")
    fun observeAll(): Flow<List<LocalMeteorite>>

    @Upsert
    suspend fun upsertAll(tasks: List<LocalMeteorite>)

    @Query("DELETE FROM meteorite")
    suspend fun deleteAll()
}