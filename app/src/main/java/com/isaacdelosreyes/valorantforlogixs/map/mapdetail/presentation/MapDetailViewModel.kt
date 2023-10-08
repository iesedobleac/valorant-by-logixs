package com.isaacdelosreyes.valorantforlogixs.map.mapdetail.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isaacdelosreyes.valorantforlogixs.core.data.model.map.MapByUuidDto
import com.isaacdelosreyes.valorantforlogixs.core.data.model.map.toDomain
import com.isaacdelosreyes.valorantforlogixs.core.data.repository.NetworkResult
import com.isaacdelosreyes.valorantforlogixs.map.mapdetail.domain.usecase.GetMapByUuidUseCase
import com.isaacdelosreyes.valorantforlogixs.utils.MAP_UUID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
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
        getMapInformation()
    }

    fun getMapInformation() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val call = getMapByUuidUseCase(mapUuid.orEmpty())) {

                is NetworkResult.Success -> {
                    Timber.i(call.data.toString())
                    parseMapDataAndSetState(call)
                }

                is NetworkResult.Error -> {
                    Timber.e(message = call.message)

                    withContext(Dispatchers.Main) {
                        state = state.copy(
                            showLoaderComponent = false,
                            showErrorScreen = true
                        )
                    }
                }

                is NetworkResult.Exception -> {
                    withContext(Dispatchers.Main) {
                        state = state.copy(
                            showLoaderComponent = false,
                            showErrorScreen = true
                        )
                    }
                }
            }
        }
    }

    private suspend fun parseMapDataAndSetState(call: NetworkResult.Success<MapByUuidDto>) {
        val map = call.data.map.toDomain()

        withContext(Dispatchers.Main) {
            state = state.copy(
                map = map,
                showLoaderComponent = false,
                showErrorScreen = false
            )
        }
    }
}