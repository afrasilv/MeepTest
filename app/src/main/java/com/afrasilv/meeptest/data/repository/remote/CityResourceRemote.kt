package com.afrasilv.meeptest.data.repository.remote

import com.afrasilv.meeptest.data.model.Resource
import com.afrasilv.meeptest.data.repository.CityResourcesRepositoryInterface
import com.afrasilv.meeptest.data.repository.Result

class CityResourceRemote(private val remoteInterface: RemoteInterface) : CityResourcesRepositoryInterface {
    override suspend fun getCityInfoByLatLng(): Result<Resource> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}