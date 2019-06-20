package com.afrasilv.meeptest

import androidx.lifecycle.MutableLiveData
import com.afrasilv.meeptest.base.BaseViewData
import com.afrasilv.meeptest.base.BaseViewModel
import com.afrasilv.meeptest.extensions.default

class MainViewModel : BaseViewModel<MainViewModel.ViewData>() {

    //region Databinding
    override var mViewData: ViewData = ViewData()

    inner class ViewData(
        val test: MutableLiveData<String> = MutableLiveData<String>().default("test")
    ) : BaseViewData()
    //endregion
}