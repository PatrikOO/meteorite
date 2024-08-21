package com.patrik.meteorite.data

import com.patrik.meteorite.AppConfig
import com.patrik.meteorite.data.source.local.LocalMeteorite
import com.patrik.meteorite.data.source.local.MeteoriteDao
import com.patrik.meteorite.data.source.local.toExternal
import com.patrik.meteorite.data.source.local.toLocal
import com.patrik.meteorite.data.source.network.MeteoriteNetworkDataSource
import com.patrik.meteorite.di.ApplicationScope
import com.patrik.meteorite.di.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MeteoriteRepository @Inject constructor(
    private val localDataSource: MeteoriteDao,
    private val networkDataSource: MeteoriteNetworkDataSource,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    @ApplicationScope private val scope: CoroutineScope,
) {

    private var loaded = false
    private val _meteorites = MutableStateFlow(emptyList<Meteorite>())
    val meteorites: StateFlow<List<Meteorite>> = _meteorites

    suspend fun loadMeteorites() {
        if (!loaded) {
            loaded = true

            val validMeteorites = networkDataSource.loadMeteorites().filter { it.isValidAndAfterDate(AppConfig.FROM_DATE) }
            localDataSource.deleteAll()
            localDataSource.upsertAll(validMeteorites.toLocal())
            loaded = true
        }

        _meteorites.value = withContext(Dispatchers.IO) {
            localDataSource.observeAll().toExternal()
        }
    }
}