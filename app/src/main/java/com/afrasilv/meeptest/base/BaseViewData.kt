package com.afrasilv.meeptest.base

import androidx.lifecycle.MutableLiveData
import com.afrasilv.meeptest.extensions.default

open class BaseViewData (
    val isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>().default(false)
)