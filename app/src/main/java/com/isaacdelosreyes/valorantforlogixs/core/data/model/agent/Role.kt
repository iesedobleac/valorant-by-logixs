package com.isaacdelosreyes.valorantforlogixs.core.data.model.agent

data class Role(
    val displayIcon: String,
    val displayName: String,
)

fun RoleDto.toDomain() =
    Role(
        displayIcon = displayIcon.orEmpty(),
        displayName = displayName.orEmpty(),
    )