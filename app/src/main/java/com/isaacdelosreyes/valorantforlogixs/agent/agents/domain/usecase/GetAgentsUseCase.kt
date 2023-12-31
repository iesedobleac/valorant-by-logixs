package com.isaacdelosreyes.valorantforlogixs.agent.agents.domain.usecase

import com.isaacdelosreyes.valorantforlogixs.core.data.repository.ValorantRepository
import javax.inject.Inject

class GetAgentsUseCase @Inject constructor(
    private val repository: ValorantRepository
) {

    suspend operator fun invoke() = repository.getAgents()
}