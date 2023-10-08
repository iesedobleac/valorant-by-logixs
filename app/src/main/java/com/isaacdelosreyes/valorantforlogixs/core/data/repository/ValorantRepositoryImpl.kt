package com.isaacdelosreyes.valorantforlogixs.core.data.repository

import com.isaacdelosreyes.valorantforlogixs.core.data.model.agent.AgentByUuidDto
import com.isaacdelosreyes.valorantforlogixs.core.data.model.agent.AgentsDto
import com.isaacdelosreyes.valorantforlogixs.core.data.model.map.MapByUuidDto
import com.isaacdelosreyes.valorantforlogixs.core.data.model.map.MapsDto
import com.isaacdelosreyes.valorantforlogixs.core.data.remote.retrofit.ValorantWs
import javax.inject.Inject

interface ValorantRepository {

    suspend fun getAgents(): NetworkResult<AgentsDto>
    suspend fun getMaps(): NetworkResult<MapsDto>
    suspend fun getAgentByUuid(uuid: String): NetworkResult<AgentByUuidDto>
    suspend fun getMapByUuid(uuid: String): NetworkResult<MapByUuidDto>
}

class ValorantRepositoryImpl @Inject constructor(
    private val valorantWs: ValorantWs
) : ValorantRepository {

    override suspend fun getAgents() = handleApi {
        valorantWs.getAllAgents()
    }

    override suspend fun getMaps() = handleApi {
        valorantWs.getAllMaps()
    }

    override suspend fun getAgentByUuid(uuid: String) = handleApi {
        valorantWs.getAgentByUuid(uuid)
    }

    override suspend fun getMapByUuid(uuid: String) = handleApi {
        valorantWs.getMapByUuid(uuid)
    }

}