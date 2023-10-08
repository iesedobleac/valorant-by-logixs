package com.isaacdelosreyes.valorantforlogixs.agentdetail.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isaacdelosreyes.valorantforlogixs.agentdetail.domain.usecase.GetAgentByUuidUseCase
import com.isaacdelosreyes.valorantforlogixs.core.data.model.agent.Ability
import com.isaacdelosreyes.valorantforlogixs.core.data.model.agent.toDomain
import com.isaacdelosreyes.valorantforlogixs.core.data.repository.NetworkResult
import com.isaacdelosreyes.valorantforlogixs.utils.AGENT_UUID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAgentByUuidUseCase: GetAgentByUuidUseCase
) : ViewModel() {

    var state by mutableStateOf(AgentDetailState())
        private set

    private val agentUuid: String? = savedStateHandle[AGENT_UUID]

    init {
        viewModelScope.launch(Dispatchers.IO) {
            when (val call = getAgentByUuidUseCase(agentUuid.orEmpty())) {

                is NetworkResult.Error -> {

                }

                is NetworkResult.Exception -> {

                }

                is NetworkResult.Success -> {
                    val agent = call.data.agent.toDomain()

                    state = state.copy(
                        agent = agent,
                        selectedAbility = agent?.abilities?.get(0)
                    )
                }
            }
        }
    }

    fun changeSelectedAbility(ability: Ability) {
        state = state.copy(
            selectedAbility = ability
        )
    }
}