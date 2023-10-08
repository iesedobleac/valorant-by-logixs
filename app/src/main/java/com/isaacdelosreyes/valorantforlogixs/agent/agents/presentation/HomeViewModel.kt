package com.isaacdelosreyes.valorantforlogixs.agent.agents.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.isaacdelosreyes.valorantforlogixs.agent.agents.domain.usecase.GetAgentsUseCase
import com.isaacdelosreyes.valorantforlogixs.core.data.model.agent.AgentsDto
import com.isaacdelosreyes.valorantforlogixs.core.data.model.agent.toDomain
import com.isaacdelosreyes.valorantforlogixs.core.data.repository.NetworkResult
import com.isaacdelosreyes.valorantforlogixs.core.di.IoDispatcher
import com.isaacdelosreyes.valorantforlogixs.core.di.MainDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAgentsFromRemoteUseCase: GetAgentsUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @MainDispatcher private val mainDispatcher: CoroutineDispatcher,
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        getAgents()
    }

    fun getAgents() {
        state = state.copy(showLoaderComponent = true)

        viewModelScope.launch(ioDispatcher) {
            when (val call = getAgentsFromRemoteUseCase()) {

                is NetworkResult.Success -> {
                    Timber.i(call.data.toString())
                    parseDataAndSetState(call)
                }

                is NetworkResult.Error -> {
                    Timber.e(message = call.message)

                    withContext(mainDispatcher) {
                        state = state.copy(
                            showErrorScreen = true,
                            showLoaderComponent = false
                        )
                    }
                }

                is NetworkResult.Exception -> {
                    FirebaseCrashlytics.getInstance().recordException(call.e)

                    withContext(mainDispatcher) {
                        state = state.copy(
                            showErrorScreen = true,
                            showLoaderComponent = false
                        )
                    }
                }
            }
        }
    }

    private suspend fun parseDataAndSetState(call: NetworkResult.Success<AgentsDto>) {
        val agents = call.data.agents
            ?.filter { !it.background.isNullOrEmpty() }
            ?.distinctBy { it.displayName }
            ?.map { it.toDomain() }

        withContext(mainDispatcher) {
            state = state.copy(
                agents = agents.orEmpty(),
                showErrorScreen = false,
                showLoaderComponent = false
            )
        }
    }
}