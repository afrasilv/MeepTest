package com.afrasilv.meeptest

import androidx.lifecycle.Observer
import com.afrasilv.meeptest.base.BaseActivity
import com.afrasilv.meeptest.databinding.MainActivityBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<MainActivityBinding, MainViewModel>() {
    override val mViewModel: MainViewModel by viewModel()

    //region BaseActivity functions
    override fun getLayoutId(): Int = R.layout.main_activity
    override fun getBindingVariable(): Int = BR.mainViewModel

    override fun attachObserver() {
        mViewModel.getViewCommand().observe(this, Observer {

        })
    }

    override fun configureToolbar() {
        setSupportActionBar(findViewById(R.id.main_toolbar))
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onSupportNavigateUp() = true
    //endregion


}
