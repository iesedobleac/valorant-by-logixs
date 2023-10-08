package com.isaacdelosreyes.valorantforlogixs.core.data.model.map

data class CalloutDto(
    val location: LocationDto?,
    val regionName: String?,
    val superRegionName: String?
)

fun CalloutDto.toDomain() =
    Callout(
        location = location?.toDomain() ?: Location(
            x = 0.0, y = 0.0
        ),
        regionName = regionName.orEmpty(),
        superRegionName = superRegionName.orEmpty()
    )