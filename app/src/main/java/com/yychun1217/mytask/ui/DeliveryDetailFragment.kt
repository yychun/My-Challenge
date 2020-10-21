package com.yychun1217.mytask.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.yychun1217.mytask.R
import com.yychun1217.mytask.databinding.FragmentDeliveryDetailBinding
import com.yychun1217.mytask.ui.navigation.INavComponent
import com.yychun1217.mytask.viewmodel.DeliveryDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class DeliveryDetailFragment : Fragment() {
    companion object {
        private const val KEY_ID = "KEY_ID"

        fun toBundle(id: Long): Bundle = bundleOf(
            KEY_ID to id,
        )
    }

    @Inject
    lateinit var viewModel: DeliveryDetailViewModel
    private lateinit var binding: FragmentDeliveryDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentDeliveryDetailBinding.inflate(inflater).also {
        it.viewModel = this.viewModel
        it.lifecycleOwner = viewLifecycleOwner
        this.binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigationUi()

        viewModel.deliveryAndRoute.observe(viewLifecycleOwner) {
            it?.let {
                setGoodsPicture(it.goodsPicture)
            }
        }

        arguments?.getLong(KEY_ID)?.let {
            viewModel.getDeliveryAndRouteByRouteID(it)
        } ?: kotlin.run {
            Timber.w("Key $KEY_ID must exist in arguments to instantiate DeliveryDetailFragment")
            findNavController().popBackStack()
        }
    }

    private fun setupNavigationUi() {
        if (INavComponent.isTwoPaneMode(requireContext())) return
        val viewId = R.id.nav_master_fragment
        val navController = Navigation.findNavController(requireActivity(), viewId)
        val toolbar: Toolbar = requireActivity().findViewById(R.id.toolbar)
        val appBinding = AppBarConfiguration(navController.graph)
        NavigationUI.setupWithNavController(toolbar, navController, appBinding)
    }

    private fun setGoodsPicture(pictureUrl: String) {
        with(binding.imageDeliveryDetailGoods) {
            if (tag == pictureUrl) return
            Glide.with(this)
                .load(pictureUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(ColorDrawable(Color.TRANSPARENT))
                .into(this)
            tag = pictureUrl
        }
    }
}