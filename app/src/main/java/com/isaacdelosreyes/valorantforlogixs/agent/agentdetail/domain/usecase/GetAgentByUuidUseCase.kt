package com.isaacdelosreyes.valorantforlogixs.agent.agentdetail.domain.usecase

import com.isaacdelosreyes.valorantforlogixs.core.data.repository.ValorantRepository
import javax.inject.Inject

class GetAgentByUuidUseCase @Inject constructor(
    private val repository: ValorantRepository
) {

    suspend operator fun invoke(uuid: String) = repository.getAgentByUuid(uuid)
}