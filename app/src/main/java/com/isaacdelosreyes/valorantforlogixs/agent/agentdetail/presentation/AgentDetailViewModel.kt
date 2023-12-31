package com.isaacdelosreyes.valorantforlogixs.agent.agentdetail.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isaacdelosreyes.valorantforlogixs.agent.agentdetail.domain.usecase.GetAgentByUuidUseCase
import com.isaacdelosreyes.valorantforlogixs.core.data.model.agent.Ability
import com.isaacdelosreyes.valorantforlogixs.core.data.model.agent.AgentByUuidDto
import com.isaacdelosreyes.valorantforlogixs.core.data.model.agent.toDomain
import com.isaacdelosreyes.valorantforlogixs.core.data.repository.NetworkResult
import com.isaacdelosreyes.valorantforlogixs.core.di.IoDispatcher
import com.isaacdelosreyes.valorantforlogixs.core.di.MainDispatcher
import com.isaacdelosreyes.valorantforlogixs.utils.AGENT_UUID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AgentDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getAgentByUuidUseCase: GetAgentByUuidUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
) : ViewModel() {

    var state by mutableStateOf(AgentDetailState())
        private set

    private val agentUuid: String? = savedStateHandle[AGENT_UUID]

    init {
        getAgentInformation()
    }

    fun getAgentInformation() {
        viewModelScope.launch(ioDispatcher) {
            when (val call = getAgentByUuidUseCase(agentUuid.orEmpty())) {

                is NetworkResult.Success -> {
                    Timber.i(call.data.toString())
                    parseAgentDataAndSetState(call)
                }

                is NetworkResult.Error -> {
                    Timber.e(message = call.message)

                    withContext(mainDispatcher) {
                        state = state.copy(
                            showErrorScreen = true,
                            showLoaderComponent = true
                        )
                    }
                }

                is NetworkResult.Exception -> {
                    withContext(mainDispatcher) {
                        state = state.copy(
                            showErrorScreen = true,
                            showLoaderComponent = true
                        )
                    }
                }
            }
        }
    }

    private suspend fun parseAgentDataAndSetState(call: NetworkResult.Success<AgentByUuidDto>) {
        val agent = call.data.agent.toDomain()

        withContext(mainDispatcher) {
            state = state.copy(
                agent = agent,
                selectedAbility = agent.abilities[0],
                showErrorScreen = false,
                showLoaderComponent = false
            )
        }
    }

    fun changeSelectedAbility(ability: Ability) {
        state = state.copy(
            selectedAbility = ability
        )
    }
}