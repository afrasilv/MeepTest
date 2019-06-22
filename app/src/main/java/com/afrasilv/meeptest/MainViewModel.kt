package com.afrasilv.meeptest

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.afrasilv.meeptest.base.BaseViewData
import com.afrasilv.meeptest.base.BaseViewModel
import com.afrasilv.meeptest.data.model.Resource
import com.afrasilv.meeptest.data.model.ResourceType
import com.afrasilv.meeptest.data.repository.CityRersourcesRepository
import com.afrasilv.meeptest.data.repository.Result
import com.afrasilv.meeptest.extensions.default
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel(private val cityRersourcesRepository: CityRersourcesRepository) : BaseViewModel<MainViewModel.ViewData>() {

    //region Databinding
    override var mViewData: ViewData = ViewData()

    inner class ViewData(
        val resourcesList: MutableLiveData<List<Resource>> = MutableLiveData<List<Resource>>().default(emptyList())
    ) : BaseViewData()
    //endregion

    init {
        coroutineContext.plus(
            launch(Dispatchers.IO) {
                val lowerLeftLatLon = "38.711046,-9.160096"
                val upperRightLatLon = "38.739429,-9.137115"
                when(val response = cityRersourcesRepository.getCityInfoByLatLng("lisboa",lowerLeftLatLon, upperRightLatLon)) {
                    is Result.Success -> {
                        response.data.forEach { it.apply {
                            resourceTypeEnum = try {
                                ResourceType.valueOf("R" + it.companyZoneId.toString())
                            } catch (e: Exception) {
                                ResourceType.UNKNOWN
                            }
                        }}
                        mViewData.resourcesList.postValue(response.data)
                    }
                    is Result.Error -> {} //TODO show error
                }
            }
        )
    }
}