package com.isaacdelosreyes.valorantforlogixs.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.isaacdelosreyes.valorantforlogixs.core.data.model.agent.AgentsDto
import com.isaacdelosreyes.valorantforlogixs.core.data.model.agent.toDomain
import com.isaacdelosreyes.valorantforlogixs.core.data.repository.NetworkResult
import com.isaacdelosreyes.valorantforlogixs.home.domain.usecase.GetAgentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAgentsFromRemoteUseCase: GetAgentsUseCase
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        getAgents()
    }

    fun getAgents() {
        state = state.copy(showLoaderComponent = true)

        viewModelScope.launch(Dispatchers.IO) {
            when (val call = getAgentsFromRemoteUseCase()) {

                is NetworkResult.Success -> {
                    Timber.i(call.data.toString())
                    parseDataAndSetState(call)
                }

                is NetworkResult.Error -> {
                    Timber.e(message = call.message)

                    withContext(Dispatchers.Main) {
                        state = state.copy(
                            showErrorScreen = true,
                            showLoaderComponent = false
                        )
                    }
                }

                is NetworkResult.Exception -> {
                    FirebaseCrashlytics.getInstance().recordException(call.e)

                    withContext(Dispatchers.Main) {
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

        withContext(Dispatchers.Main) {
            state = state.copy(
                agents = agents.orEmpty(),
                showErrorScreen = false,
                showLoaderComponent = false
            )
        }
    }
}