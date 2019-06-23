package com.afrasilv.meeptest.ui.details

import androidx.lifecycle.MutableLiveData
import com.afrasilv.meeptest.base.BaseViewCommand
import com.afrasilv.meeptest.base.BaseViewData
import com.afrasilv.meeptest.base.BaseViewModel
import com.afrasilv.meeptest.data.model.Resource
import com.afrasilv.meeptest.data.repository.CityRersourcesRepository
import com.afrasilv.meeptest.extensions.default

class DetailsMarkerViewModel: BaseViewModel<DetailsMarkerViewModel.ViewData>() {

    var resourceData: Resource? = null
    //region Databinding
    override var mViewData: ViewData = ViewData()

    inner class ViewData(
        val markerId: MutableLiveData<String> = MutableLiveData<String>().default(""),
        val markerName: MutableLiveData<String> = MutableLiveData<String>().default(""),
        val markerLatLng: MutableLiveData<String> = MutableLiveData<String>().default(""),
        val markerCompanyId: MutableLiveData<Int> = MutableLiveData<Int>().default(0),
        val isCar: MutableLiveData<Boolean> = MutableLiveData<Boolean>().default(false),
        val isMoped: MutableLiveData<Boolean> = MutableLiveData<Boolean>().default(false),
        val isBikes: MutableLiveData<Boolean> = MutableLiveData<Boolean>().default(false),
        val carData: MutableLiveData<String> = MutableLiveData<String>().default(""),
        val batteryData: MutableLiveData<String> = MutableLiveData<String>().default(""),
        val helmetsData: MutableLiveData<String> = MutableLiveData<String>().default(""),
        val bikesData: MutableLiveData<String> = MutableLiveData<String>().default("")
        ) : BaseViewData()
    //endregion

    fun closeDialog() {
        mViewCommand.value = BaseViewCommand.DismissDialog
    }

    fun showData() {
        mViewData.markerId.value = resourceData?.id
        mViewData.markerName.value = resourceData?.name
        mViewData.markerLatLng.value = resourceData?.x.toString() + " " + resourceData?.y.toString()
        mViewData.markerCompanyId.value = resourceData?.companyZoneId
        when(resourceData?.companyZoneId) {//TODO set as constant
            467 -> {
                mViewData.isCar.value = true
                getCarData()
                getBattery()
            }
            473 -> {
                mViewData.isMoped.value = true
                getHelmets()
            }
            412 -> {
                mViewData.isBikes.value = true
                getBikesNumber()
            }
        }
    }

    fun getCarData() {
        mViewData.carData.value = if(mViewData.isCar.value!!) {
            resourceData?.model + " " + resourceData?.licencePlate
        } else {
            ""
        }
    }

    fun getBattery() {
        mViewData.batteryData.value = if(mViewData.isCar.value!!) {
            resourceData?.batteryLevel.toString()
        } else {
            ""
        }
    }

    fun getHelmets() {
        mViewData.helmetsData.value = if(mViewData.isMoped.value!!) {
            resourceData?.helmets.toString()
        } else {
            ""
        }
    }

    fun getBikesNumber() {
        mViewData.bikesData.value =  if(mViewData.isBikes.value!!) {
            resourceData?.bikesAvailable.toString()
        } else {
            ""
        }
    }
}