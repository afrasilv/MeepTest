package com.afrasilv.meeptest.ui.details

import android.os.Bundle
import androidx.lifecycle.Observer
import com.afrasilv.meeptest.BR
import com.afrasilv.meeptest.R
import com.afrasilv.meeptest.base.BaseDialogFragment
import com.afrasilv.meeptest.base.BaseViewCommand
import com.afrasilv.meeptest.data.model.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsMarkerDialogFragment : BaseDialogFragment<com.afrasilv.meeptest.databinding.DetailsMarkerDialogFragmentBinding, DetailsMarkerViewModel>() {
    override val mViewModel: DetailsMarkerViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.details_marker_dialog_fragment

    override fun getBindingVariable(): Int = BR.detailsMarkerViewModel

    override fun attachObserver() {
        mViewModel.resourceData = arguments?.getSerializable("RESOURCE_DATA") as Resource

        mViewModel.getViewCommand().observe(this, Observer {
            when (it) {
                is BaseViewCommand.DismissDialog -> dismiss()
            }
        })

        mViewModel.showData()
    }

    companion object {
        fun newInstance(resourceData: Resource): DetailsMarkerDialogFragment {
            val dialog = DetailsMarkerDialogFragment()

            // Supply num input as an argument.
            val args = Bundle()
            args.putSerializable("RESOURCE_DATA", resourceData)
            dialog.arguments = args

            return dialog
        }
    }

}