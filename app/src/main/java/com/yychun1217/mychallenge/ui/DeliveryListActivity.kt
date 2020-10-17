package com.yychun1217.mychallenge.ui

import android.os.Bundle
import androidx.core.widget.NestedScrollView
import androidx.appcompat.app.AppCompatActivity
import com.yychun1217.mychallenge.R
import com.yychun1217.mychallenge.databinding.ActivityDeliveryListBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [DeliveryDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
@AndroidEntryPoint
class DeliveryListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false
    private lateinit var binding: ActivityDeliveryListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeliveryListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = title

        if (findViewById<NestedScrollView>(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

//        if (twoPane) {
//            val fragment = DeliveryDetailFragment().apply {
//                arguments = Bundle().apply {
//                    putString(DeliveryDetailFragment.ARG_ITEM_ID, item.id)
//                }
//            }
//            parentActivity.supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.item_detail_container, fragment)
//                .commit()
//        } else {
//            val intent = Intent(v.context, DeliveryDetailActivity::class.java).apply {
//                putExtra(DeliveryDetailFragment.ARG_ITEM_ID, item.id)
//            }
//            v.context.startActivity(intent)
//        }
    }
}