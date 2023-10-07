package com.isaacdelosreyes.valorantforlogixs.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isaacdelosreyes.valorantforlogixs.core.data.model.toDomain
import com.isaacdelosreyes.valorantforlogixs.core.data.repository.NetworkResult
import com.isaacdelosreyes.valorantforlogixs.home.domain.usecase.GetAgentsFromRemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAgentsFromRemoteUseCase: GetAgentsFromRemoteUseCase
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {

            when (val call = getAgentsFromRemoteUseCase()) {

                is NetworkResult.Success -> {

                    val agents = call.data.agents
                        ?.filter { !it.background.isNullOrEmpty() }
                        ?.distinctBy { it.displayName }
                        ?.map { it.toDomain() }

                    state = state.copy(
                        agents = agents.orEmpty()
                    )
                }

                is NetworkResult.Error -> {
                    //no-op
                }

                is NetworkResult.Exception -> {
                    //no-op
                }
            }
        }
    }
}