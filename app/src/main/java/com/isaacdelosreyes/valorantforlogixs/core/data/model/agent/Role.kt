package com.isaacdelosreyes.valorantforlogixs.core.data.model.agent

data class Role(
    val assetPath: String,
    val description: String,
    val displayIcon: String,
    val displayName: String,
    val uuid: String
)

fun RoleDto.toDomain() =
    Role(
        assetPath = assetPath.orEmpty(),
        description = description.orEmpty(),
        displayIcon = displayIcon.orEmpty(),
        displayName = displayName.orEmpty(),
        uuid = uuid.orEmpty()
    )