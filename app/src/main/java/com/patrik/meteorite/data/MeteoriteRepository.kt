package com.patrik.meteorite.data

import com.patrik.meteorite.data.source.local.MeteoriteDao
import com.patrik.meteorite.data.source.local.toExternal
import com.patrik.meteorite.data.source.local.toLocal
import com.patrik.meteorite.data.source.network.MeteoriteNetworkDataSource
import com.patrik.meteorite.di.ApplicationScope
import com.patrik.meteorite.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MeteoriteRepository @Inject constructor(
    private val localDataSource: MeteoriteDao,
    private val networkDataSource: MeteoriteNetworkDataSource,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    @ApplicationScope private val scope: CoroutineScope,
) {

    fun observeMeteorites(): Flow<List<Meteorite>> {
        return localDataSource.observeAll().map { meteorites ->
            meteorites.toExternal()
        }
    }

    suspend fun refresh() {
        val networkMeteorites = networkDataSource.loadMeteorites()
        val validMeteorites = networkMeteorites.filter { it.isValid() }
        localDataSource.deleteAll()
        localDataSource.upsertAll(validMeteorites.toLocal())
    }
}