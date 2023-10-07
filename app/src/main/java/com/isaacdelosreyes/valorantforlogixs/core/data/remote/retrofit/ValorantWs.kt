package com.isaacdelosreyes.valorantforlogixs.core.data.remote.retrofit

import com.isaacdelosreyes.valorantforlogixs.core.data.model.AgentsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ValorantWs {

    @GET("agents")
    suspend fun getAllAgents(
        @Query("language") language: String = "es-ES"
    ): Response<AgentsDto>

}