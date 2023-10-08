package com.isaacdelosreyes.valorantforlogixs.core.data.remote.retrofit

import com.isaacdelosreyes.valorantforlogixs.core.data.model.agent.AgentByUuidDto
import com.isaacdelosreyes.valorantforlogixs.core.data.model.agent.AgentsDto
import com.isaacdelosreyes.valorantforlogixs.core.data.model.map.MapByUuidDto
import com.isaacdelosreyes.valorantforlogixs.core.data.model.map.MapsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ValorantWs {

    @GET("agents")
    suspend fun getAllAgents(
        @Query("language") language: String = "es-ES"
    ): Response<AgentsDto>

    @GET("maps")
    suspend fun getAllMaps(
        @Query("language") language: String = "es-ES"
    ): Response<MapsDto>

    @GET("agents/{agentUuid}")
    suspend fun getAgentByUuid(
        @Path("agentUuid") uuid: String, @Query("language") language: String = "es-ES"
    ): Response<AgentByUuidDto>

    @GET("maps/{mapUuid}")
    suspend fun getMapByUuid(
        @Path("mapUuid") uuid: String, @Query("language") language: String = "es-ES"
    ): Response<MapByUuidDto>
}