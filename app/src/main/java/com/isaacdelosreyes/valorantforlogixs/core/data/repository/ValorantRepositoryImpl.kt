package com.isaacdelosreyes.valorantforlogixs.core.data.repository

import com.isaacdelosreyes.valorantforlogixs.core.data.model.AgentsDto
import com.isaacdelosreyes.valorantforlogixs.core.data.remote.retrofit.ValorantWs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface ValorantRepository {

    suspend fun getAgents(): NetworkResult<AgentsDto>

}

class ValorantRepositoryImpl @Inject constructor(
    private val valorantWs: ValorantWs
) : ValorantRepository {

    override suspend fun getAgents() = handleApi {
        withContext(Dispatchers.IO) {
            valorantWs.getAllAgents()
        }
    }
}