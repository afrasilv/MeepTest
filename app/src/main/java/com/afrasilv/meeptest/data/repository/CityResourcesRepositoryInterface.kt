package com.afrasilv.meeptest.data.repository

import com.afrasilv.meeptest.data.model.Resource
import com.afrasilv.meeptest.data.repository.remote.CityResourceRemote

interface CityResourcesRepositoryInterface {
    suspend fun getCityInfoByLatLng( cityName: String, lowerLeftLatLon: String, upperRightLatLon: String) : Result<List<Resource>>
}

class CityRersourcesRepository(
    private val cityResourceRemote: CityResourceRemote
): CityResourcesRepositoryInterface {
    override suspend fun getCityInfoByLatLng( cityName: String, lowerLeftLatLon: String, upperRightLatLon: String): Result<List<Resource>> {
        return cityResourceRemote.getCityInfoByLatLng(cityName, lowerLeftLatLon, upperRightLatLon)
    }
}