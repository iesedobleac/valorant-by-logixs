package com.isaacdelosreyes.valorantforlogixs.mapdetail.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isaacdelosreyes.valorantforlogixs.core.data.model.map.toDomain
import com.isaacdelosreyes.valorantforlogixs.core.data.repository.NetworkResult
import com.isaacdelosreyes.valorantforlogixs.mapdetail.domain.usecase.GetMapByUuidUseCase
import com.isaacdelosreyes.valorantforlogixs.utils.MAP_UUID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMapByUuidUseCase: GetMapByUuidUseCase
) : ViewModel() {

    var state by mutableStateOf(MapDetailState())
        private set

    private val mapUuid: String? = savedStateHandle[MAP_UUID]

    init {
        viewModelScope.launch(Dispatchers.IO) {
            when (val call = getMapByUuidUseCase(mapUuid.orEmpty())) {

                is NetworkResult.Error -> {

                }

                is NetworkResult.Exception -> {

                }

                is NetworkResult.Success -> {
                    val map = call.data.map.toDomain()

                    state = state.copy(
                        map = map,
                    )
                }
            }
        }
    }
}