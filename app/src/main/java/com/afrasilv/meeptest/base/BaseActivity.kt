package com.afrasilv.meeptest.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<*>> : AppCompatActivity() {

    abstract val mViewModel: V
    private lateinit var mViewDataBinding: T

    //region Lifecycle methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // To bind layout with Activity
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())

        // To bind layout with ViewModel on a property (BR.xxx)
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding.executePendingBindings()

        // To observe changes of LiveData in this binding
        mViewDataBinding.lifecycleOwner = this

        // To use lifecycle events on ViewModel
        lifecycle.addObserver(mViewModel)

        attachObserver()
        configureToolbar()
    }

    override fun onDestroy() {
        lifecycle.removeObserver(mViewModel)
        super.onDestroy()
    }
    //endregion

    //region Abstract methods
    abstract fun getLayoutId(): Int

    abstract fun getBindingVariable(): Int
    abstract fun attachObserver()
    abstract fun configureToolbar()

    //To ensure the Back button works properly
    abstract override fun onSupportNavigateUp(): Boolean
    //endregion
}