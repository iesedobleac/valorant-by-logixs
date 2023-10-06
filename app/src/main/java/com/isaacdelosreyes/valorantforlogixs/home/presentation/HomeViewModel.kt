package com.isaacdelosreyes.valorantforlogixs.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isaacdelosreyes.valorantforlogixs.home.domain.usecase.GetAgentsFromRemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAgentsFromRemoteUseCase: GetAgentsFromRemoteUseCase
): ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val data = getAgentsFromRemoteUseCase()
            println(data.agents)
        }
    }
}