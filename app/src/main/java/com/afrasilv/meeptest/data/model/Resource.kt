package com.afrasilv.meeptest.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import java.io.Serializable

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
) : Serializable

enum class ResourceType(val markerColor: Float) {
    R402(BitmapDescriptorFactory.HUE_AZURE),
    R382(BitmapDescriptorFactory.HUE_BLUE),
    R545(BitmapDescriptorFactory.HUE_RED),
    R467(BitmapDescriptorFactory.HUE_GREEN),
    R473(BitmapDescriptorFactory.HUE_ORANGE),
    R412(BitmapDescriptorFactory.HUE_YELLOW),
    UNKNOWN(BitmapDescriptorFactory.HUE_MAGENTA)
}