package com.afrasilv.meeptest.data.repository

import com.afrasilv.meeptest.data.model.Resource
import com.afrasilv.meeptest.data.repository.remote.CityResourceRemote

interface CityResourcesRepositoryInterface {
    suspend fun getCityInfoByLatLng() : Result<Resource>
}

class CityRersourcesRepository(
    private val cityResourceRemote: CityResourceRemote
): CityResourcesRepositoryInterface {
    override suspend fun getCityInfoByLatLng(): Result<Resource> {
        return cityResourceRemote.getCityInfoByLatLng()
    }
}