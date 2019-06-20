package com.afrasilv.meeptest.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel<*>> : Fragment() {

    abstract val mViewModel: V
    private lateinit var mViewDataBinding: T
    private lateinit var mActivity: BaseActivity<*, *>

    //region Lifecycle methods
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mActivity = activity as BaseActivity<*, *>
        lifecycle.addObserver(mViewModel)
        attachObserver()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return mViewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding.executePendingBindings()
        mViewDataBinding.lifecycleOwner = this
    }

    override fun onDestroyView() {
        lifecycle.removeObserver(mViewModel)
        super.onDestroyView()
    }
    //endregion

    fun getViewDataBinding(): T = mViewDataBinding

    fun getBaseActivity(): BaseActivity<*, *> = mActivity

    //region Abstract methods
    abstract fun getLayoutId(): Int
    abstract fun getBindingVariable(): Int
    abstract fun attachObserver()
    //endregion
}