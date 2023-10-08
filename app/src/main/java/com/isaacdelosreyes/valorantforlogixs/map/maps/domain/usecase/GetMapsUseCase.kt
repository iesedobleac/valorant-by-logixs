package com.isaacdelosreyes.valorantforlogixs.map.maps.domain.usecase

import com.isaacdelosreyes.valorantforlogixs.core.data.repository.ValorantRepository
import javax.inject.Inject

class GetMapsUseCase @Inject constructor(
    private val repository: ValorantRepository
) {

    suspend operator fun invoke() = repository.getMaps()
}