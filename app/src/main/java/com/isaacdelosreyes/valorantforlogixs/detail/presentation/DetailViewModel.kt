package com.isaacdelosreyes.valorantforlogixs.detail.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isaacdelosreyes.valorantforlogixs.core.data.model.Ability
import com.isaacdelosreyes.valorantforlogixs.core.data.model.toDomain
import com.isaacdelosreyes.valorantforlogixs.core.data.repository.NetworkResult
import com.isaacdelosreyes.valorantforlogixs.detail.domain.usecase.GetAgentByUuidUseCase
import com.isaacdelosreyes.valorantforlogixs.utils.AGENT_UUID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAgentByUuidUseCase: GetAgentByUuidUseCase
) : ViewModel() {

    var state by mutableStateOf(DetailState())
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