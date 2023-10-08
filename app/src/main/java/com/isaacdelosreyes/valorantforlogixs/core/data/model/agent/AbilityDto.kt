package com.isaacdelosreyes.valorantforlogixs.core.data.model.agent

data class AbilityDto(
    val description: String?,
    val displayIcon: String?,
    val displayName: String?,
    val slot: String?
)

fun AbilityDto.toDomain() =
    Ability(
        description = description.orEmpty(),
        displayIcon = displayIcon.orEmpty(),
        displayName = displayName.orEmpty(),
        slot = slot.orEmpty()
    )