package com.afrasilv.meeptest.base

import android.widget.Toast
import androidx.annotation.StringRes

sealed class BaseViewCommand {
    class ShowToast(@StringRes val msg: Int, val duration: Int = Toast.LENGTH_SHORT) : BaseViewCommand()
}