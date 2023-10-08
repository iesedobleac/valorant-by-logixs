package com.isaacdelosreyes.valorantforlogixs.maps.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isaacdelosreyes.valorantforlogixs.core.data.model.map.toDomain
import com.isaacdelosreyes.valorantforlogixs.core.data.repository.NetworkResult
import com.isaacdelosreyes.valorantforlogixs.maps.domain.usecase.GetMapsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val getMapsUseCase: GetMapsUseCase
) : ViewModel() {

    var state by mutableStateOf(MapsState())
        private set

    init {
        viewModelScope.launch(Dispatchers.IO) {

            when (val call = getMapsUseCase()) {

                is NetworkResult.Success -> {

                    val maps = call.data.maps?.map { it.toDomain() }
                        ?.filter {
                            !it.displayIcon.isNullOrEmpty()
                                    && !it.narrativeDescription.isNullOrEmpty()
                        }

                    state = state.copy(
                        maps = maps.orEmpty()
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