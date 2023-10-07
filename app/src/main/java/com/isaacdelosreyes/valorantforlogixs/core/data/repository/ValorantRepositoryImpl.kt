package com.isaacdelosreyes.valorantforlogixs.core.data.repository

import com.isaacdelosreyes.valorantforlogixs.core.data.model.AgentByUuidDto
import com.isaacdelosreyes.valorantforlogixs.core.data.model.AgentsDto
import com.isaacdelosreyes.valorantforlogixs.core.data.remote.retrofit.ValorantWs
import javax.inject.Inject

interface ValorantRepository {

    suspend fun getAgents(): NetworkResult<AgentsDto>
    suspend fun getAgentByUuid(uuid: String): NetworkResult<AgentByUuidDto>

}

class ValorantRepositoryImpl @Inject constructor(
    private val valorantWs: ValorantWs
) : ValorantRepository {

    override suspend fun getAgents() = handleApi {
        valorantWs.getAllAgents()
    }

    override suspend fun getAgentByUuid(uuid: String) = handleApi {
        valorantWs.getAgentByUuid(uuid)
    }
}