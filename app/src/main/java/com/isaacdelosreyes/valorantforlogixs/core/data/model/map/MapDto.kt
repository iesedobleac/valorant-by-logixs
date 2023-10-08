package com.isaacdelosreyes.valorantforlogixs.core.data.model.map

data class MapDto(
    val assetPath: String?,
    val callouts: List<CalloutDto>?,
    val coordinates: String?,
    val displayIcon: String?,
    val displayName: String?,
    val listViewIcon: String?,
    val mapUrl: String?,
    val narrativeDescription: String?,
    val splash: String?,
    val tacticalDescription: String?,
    val uuid: String?,
    val xMultiplier: Double?,
    val xScalarToAdd: Double?,
    val yMultiplier: Double?,
    val yScalarToAdd: Double?
)

fun MapDto.toDomain() =
    Map(
        assetPath = assetPath.orEmpty(),
        callouts = callouts?.map { it.toDomain() }.orEmpty(),
        coordinates = coordinates.orEmpty(),
        displayIcon = displayIcon.orEmpty(),
        displayName = displayName.orEmpty(),
        listViewIcon = listViewIcon.orEmpty(),
        mapUrl = mapUrl.orEmpty(),
        narrativeDescription = narrativeDescription.orEmpty(),
        splash = splash.orEmpty(),
        tacticalDescription = tacticalDescription.orEmpty(),
        uuid = uuid.orEmpty(),
        xMultiplier = xMultiplier ?: 0.0,
        xScalarToAdd = xScalarToAdd ?: 0.0,
        yMultiplier = yMultiplier ?: 0.0,
        yScalarToAdd = yScalarToAdd ?: 0.0
    )