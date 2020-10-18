package com.yychun1217.mychallenge.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.yychun1217.mychallenge.databinding.LayoutTwoPaneFragmentHostBinding
import timber.log.Timber

class TwoPaneFragmentHostLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding =
        LayoutTwoPaneFragmentHostBinding.inflate(LayoutInflater.from(context), this)

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private val twoPane: Boolean = binding.navMenuFragment != null

    init {
        Timber.d("TwoPaneFragmentHostLayout.twoPane: $twoPane")
    }
}