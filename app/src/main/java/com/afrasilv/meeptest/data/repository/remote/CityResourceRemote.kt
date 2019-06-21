package com.afrasilv.meeptest.data.repository.remote

import com.afrasilv.meeptest.data.model.Resource
import com.afrasilv.meeptest.data.repository.CityResourcesRepositoryInterface
import com.afrasilv.meeptest.data.repository.Result

class CityResourceRemote(private val remoteInterface: RemoteInterface) : CityResourcesRepositoryInterface {
    override suspend fun getCityInfoByLatLng( cityName: String, lowerLeftLatLon: String, upperRightLatLon: String): Result<List<Resource>> {
        return try {
            val responseData = remoteInterface.getCityInfoByLatLng(cityName, lowerLeftLatLon, upperRightLatLon).await()
            if (responseData.errorBody() != null) {

            }
            Result.Success(responseData.body()!!)

        } catch (e: Exception) {
            Result.Error
        }
    }
}