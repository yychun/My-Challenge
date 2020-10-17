package com.yychun1217.mychallenge.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yychun1217.mychallenge.R
import com.yychun1217.mychallenge.databinding.FragmentDeliveryDetailBinding
import com.yychun1217.mychallenge.dummy.DummyContent

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [DeliveryListActivity]
 * in two-pane mode (on tablets) or a [DeliveryDetailActivity]
 * on handsets.
 */
class DeliveryDetailFragment : Fragment() {

    /**
     * The dummy content this fragment is presenting.
     */
    private var item: DummyContent.DummyItem? = null
    private lateinit var binding: FragmentDeliveryDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = DummyContent.ITEM_MAP[it.getString(ARG_ITEM_ID)]
                activity?.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)?.title =
                    item?.content
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = FragmentDeliveryDetailBinding.inflate(inflater).apply {
        binding = this
        // Show the dummy content as text in a TextView.
        item?.let {
            itemDetail.text = it.details
        }
    }.root

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}