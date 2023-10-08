package com.isaacdelosreyes.valorantforlogixs.core.data.model.agent

data class AgentDto(
    val abilities: List<AbilityDto>?,
    val assetPath: String?,
    val background: String?,
    val backgroundGradientColors: List<String>?,
    val bustPortrait: String?,
    val description: String?,
    val developerName: String?,
    val displayIcon: String?,
    val displayIconSmall: String?,
    val displayName: String?,
    val fullPortrait: String?,
    val fullPortraitV2: String?,
    val isAvailableForTest: Boolean?,
    val isBaseContent: Boolean?,
    val isFullPortraitRightFacing: Boolean?,
    val isPlayableCharacter: Boolean?,
    val killfeedPortrait: String?,
    val role: RoleDto?,
    val uuid: String?,
)

fun AgentDto.toDomain() =
    Agent(
        abilities = abilities?.map { it.toDomain() }.orEmpty(),
        assetPath = assetPath.orEmpty(),
        background = background.orEmpty(),
        backgroundGradientColors = backgroundGradientColors.orEmpty(),
        bustPortrait = bustPortrait.orEmpty(),
        description = description.orEmpty(),
        developerName = developerName.orEmpty(),
        displayIcon = displayIcon.orEmpty(),
        displayIconSmall = displayIconSmall.orEmpty(),
        displayName = displayName.orEmpty(),
        fullPortrait = fullPortrait.orEmpty(),
        fullPortraitV2 = fullPortraitV2.orEmpty(),
        isAvailableForTest = isAvailableForTest ?: false,
        isBaseContent = isBaseContent ?: false,
        isFullPortraitRightFacing = isFullPortraitRightFacing ?: false,
        isPlayableCharacter = isPlayableCharacter ?: false,
        killfeedPortrait = killfeedPortrait.orEmpty(),
        role = role?.toDomain() ?: Role(
            assetPath = "",
            description = "",
            displayIcon = "",
            displayName = "",
            uuid = ""
        ),
        uuid = uuid.orEmpty(),
    )

