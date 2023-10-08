package com.isaacdelosreyes.valorantforlogixs.maps.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.isaacdelosreyes.valorantforlogixs.core.data.model.map.Map
import com.isaacdelosreyes.valorantforlogixs.core.data.model.map.MapsDto
import com.isaacdelosreyes.valorantforlogixs.core.data.model.map.toDomain
import com.isaacdelosreyes.valorantforlogixs.core.data.repository.NetworkResult
import com.isaacdelosreyes.valorantforlogixs.maps.domain.usecase.GetMapsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val getMapsUseCase: GetMapsUseCase
) : ViewModel() {

    var state by mutableStateOf(MapsState())
        private set

    init {
        getMaps()
    }

    fun getMaps() {
        state = state.copy(showLoaderComponent = true)

        viewModelScope.launch(Dispatchers.IO) {
            when (val call = getMapsUseCase()) {

                is NetworkResult.Success -> {
                    Timber.i(call.data.toString())
                    parseMapsDataAndSetState(call)
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

    private suspend fun parseMapsDataAndSetState(call: NetworkResult.Success<MapsDto>) {
        val maps = call.data.maps.map { it.toDomain() }
            .filter {
                needToDiscardThoseWithoutAMapOrDescription(it)
            }

        withContext(Dispatchers.Main) {
            state = state.copy(
                maps = maps,
                showErrorScreen = false,
                showLoaderComponent = false
            )
        }
    }

    private fun needToDiscardThoseWithoutAMapOrDescription(it: Map) =
        (it.displayIcon.isNotEmpty()
                && it.narrativeDescription.isNotEmpty())
}