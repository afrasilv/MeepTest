package com.afrasilv.meeptest.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import com.afrasilv.meeptest.BR
import com.afrasilv.meeptest.R
import com.afrasilv.meeptest.base.BaseActivity
import com.afrasilv.meeptest.base.BaseViewCommand
import com.afrasilv.meeptest.databinding.MainActivityBinding
import com.afrasilv.meeptest.ui.details.DetailsMarkerDialogFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<MainActivityBinding, MainViewModel>(), OnMapReadyCallback,
    GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap

    override val mViewModel: MainViewModel by viewModel()

    //region BaseActivity functions
    override fun getLayoutId(): Int = R.layout.main_activity
    override fun getBindingVariable(): Int = BR.mainViewModel

    override fun attachObserver() {
        mViewModel.getViewCommand().observe(this, Observer {
            when(it) {
                is BaseViewCommand.ShowDetails -> {
                    val ft = supportFragmentManager.beginTransaction()
                    DetailsMarkerDialogFragment.newInstance(mViewModel.getMarkerDataById(it.selectedMarkerId)).show(ft, "")
                }
            }
        })

        mViewModel.getViewData().resourcesList.observe(this, Observer { resourcesList ->
            val builder = LatLngBounds.Builder()
            resourcesList.forEach {
                val marker = MarkerOptions().position(LatLng(it.y, it.x)).icon(
                    BitmapDescriptorFactory
                        .defaultMarker(it.resourceTypeEnum!!.markerColor)).title(it.name).snippet(it.id)
                mMap.addMarker(marker)
                builder.include(marker.position)
            }
            if (resourcesList.isNotEmpty()) {
                val bounds = builder.build()
                val width = resources.displayMetrics.widthPixels
                val height = resources.displayMetrics.heightPixels
                val padding = (width * 0.10).toInt() // offset from edges of the map 10% of screen
                val cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding)
                mMap.animateCamera(cu)
            }
        })
    }

    override fun configureToolbar() {
        setSupportActionBar(findViewById(R.id.main_toolbar))
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setTitle(R.string.app_name)
    }

    override fun onSupportNavigateUp() = true
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMarkerClickListener(this)
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        mViewModel.getViewCommand().value = BaseViewCommand.ShowDetails(marker.snippet)
        return true
    }
}
