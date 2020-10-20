package com.yychun1217.mytask.ui

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.IdRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.yychun1217.mytask.R
import com.yychun1217.mytask.databinding.LayoutTwoPaneFragmentHostBinding
import com.yychun1217.mytask.ui.navigation.INavComponent
import timber.log.Timber

class TwoPaneFragmentHostLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), INavComponent {
    companion object {
        fun isMasterComponent(@IdRes destinationId: Int): Boolean = when (destinationId) {
            R.id.fragment_delivery_list -> true
            else -> false
        }

        fun isDetailComponent(@IdRes destinationId: Int): Boolean = when (destinationId) {
            R.id.fragment_delivery_detail -> true
            else -> false
        }
    }

    private val binding =
        LayoutTwoPaneFragmentHostBinding.inflate(LayoutInflater.from(context), this)
    private val isTwoPane: Boolean
        get() = context.resources.getBoolean(R.bool.isTablet)

    override fun navigate(destinationId: Int, args: Bundle?, navOptions: NavOptions?): Boolean {
        val navHostFragment = if (isTwoPane) when {
            isMasterComponent(destinationId) -> binding.navMasterFragment
            isDetailComponent(destinationId) -> binding.navDetailFragment
            else -> null
        } else binding.navMasterFragment
        return navHostFragment?.findNavController()?.navigate(destinationId, args, navOptions)?.let {
            true
        } ?: kotlin.run {
            Timber.w("No matching navHostFragment for destinationId $destinationId !")
            false
        }
    }
}