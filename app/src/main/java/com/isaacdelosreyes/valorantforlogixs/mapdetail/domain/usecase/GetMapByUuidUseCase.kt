package com.isaacdelosreyes.valorantforlogixs.mapdetail.domain.usecase

import com.isaacdelosreyes.valorantforlogixs.core.data.repository.ValorantRepository
import javax.inject.Inject

class GetMapByUuidUseCase @Inject constructor(
    private val repository: ValorantRepository
) {

    suspend operator fun invoke(uuid: String) = repository.getMapByUuid(uuid)
}