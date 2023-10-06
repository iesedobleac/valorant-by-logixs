package com.isaacdelosreyes.valorantforlogixs.core.data.repository

import com.isaacdelosreyes.valorantforlogixs.core.data.model.Data
import com.isaacdelosreyes.valorantforlogixs.core.data.remote.retrofit.ValorantWs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface ValorantRepository {

    suspend fun getAgents(): Data

}

class ValorantRepositoryImpl @Inject constructor(
    private val valorantWs: ValorantWs
) : ValorantRepository {

    override suspend fun getAgents() =
        withContext(Dispatchers.IO) {
            valorantWs.getAllAgents()
        }
}