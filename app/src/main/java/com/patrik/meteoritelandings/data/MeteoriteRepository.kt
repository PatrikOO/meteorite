package com.patrik.meteoritelandings.data

import com.patrik.meteoritelandings.data.source.local.MeteoriteDao
import com.patrik.meteoritelandings.data.source.local.toExternal
import com.patrik.meteoritelandings.data.source.local.toLocal
import com.patrik.meteoritelandings.data.source.network.MeteoriteNetworkDataSource
import com.patrik.meteoritelandings.di.ApplicationScope
import com.patrik.meteoritelandings.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MeteoriteRepository @Inject constructor(
    private val localDataSource: MeteoriteDao,
    private val networkDataSource: MeteoriteNetworkDataSource,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    @ApplicationScope private val scope: CoroutineScope,
) {

    fun observeAll(): Flow<List<Meteorite>> {
        return localDataSource.observeAll().map { meteorites ->
            meteorites.toExternal()
        }
    }

    suspend fun refresh() {
        val networkMeteorites = networkDataSource.loadMeteorites()
        localDataSource.deleteAll()
        localDataSource.upsertAll(networkMeteorites.toLocal())
    }
}