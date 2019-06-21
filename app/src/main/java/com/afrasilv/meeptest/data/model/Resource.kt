package com.afrasilv.meeptest.data.model

import com.google.android.gms.maps.model.BitmapDescriptorFactory

data class Resource (
    val id: String,
    val name: String,
    val x: Double,
    val y: Double,
    val companyZoneId: Int,
    val lon: Double?,
    val lat: Double?,
    val licencePlate: String?,
    val range: Int?,
    val batteryLevel: Int?,
    val seats: Int?,
    val model: String?,
    val resourceImageId: String?,
    val pricePerMinuteParking: Double?,
    val pricePerMinuteDriving: Double?,
    val realTimeData: Boolean?,
    val engineType: String?,
    val resourceType: String?,
    val helmets: Int?,
    val station: Boolean?,
    val availableResources: Int?,
    val spacesAvailable: Int?,
    val allowDropoff: Boolean?,
    val bikesAvailable: Int?,
    var resourceTypeEnum: ResourceType?
)

enum class ResourceType(val markerColor: Float) {
    BUS(BitmapDescriptorFactory.HUE_BLUE),
    CAR(BitmapDescriptorFactory.HUE_RED),
    ELECTRIC_CAR(BitmapDescriptorFactory.HUE_GREEN),
    MOPED(BitmapDescriptorFactory.HUE_ORANGE),
    BIKE(BitmapDescriptorFactory.HUE_YELLOW)
}