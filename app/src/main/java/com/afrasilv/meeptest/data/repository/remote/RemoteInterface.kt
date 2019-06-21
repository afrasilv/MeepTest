package com.afrasilv.meeptest.data.repository.remote

import com.afrasilv.meeptest.data.model.Resource
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteInterface {

    @GET("routers/{cityName}/resources")
    fun getCityInfoByLatLng(
        @Path("cityName") cityName: String,
        @Query(value="lowerLeftLatLon", encoded=true) lowerLeftLatLon: String,
        @Query(value="upperRightLatLon", encoded=true) upperRightLatLon: String): Deferred<Response<List<Resource>>>
}