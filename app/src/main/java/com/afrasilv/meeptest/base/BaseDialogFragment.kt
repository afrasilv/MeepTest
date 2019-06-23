package com.afrasilv.meeptest.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment

abstract class BaseDialogFragment<T : ViewDataBinding, V : BaseViewModel<*>> : DialogFragment() {
    abstract val mViewModel: V
    private lateinit var mViewDataBinding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Pick a style based on the num.
        val style = DialogFragment.STYLE_NO_TITLE
        val theme = android.R.style.Theme_Material_Light_Dialog_NoActionBar_MinWidth
        setStyle(style, theme)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        isCancelable = true
        return mViewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycle.addObserver(mViewModel)
        attachObserver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding.executePendingBindings()
        mViewDataBinding.lifecycleOwner = this
    }

    //region Abstract methods
    abstract fun getLayoutId(): Int
    abstract fun getBindingVariable(): Int
    abstract fun attachObserver()
    //endregion
}