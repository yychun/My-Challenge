package com.yychun1217.mychallenge.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.yychun1217.mychallenge.R
import com.yychun1217.mychallenge.databinding.ActivityDeliveryListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeliveryListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeliveryListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeliveryListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_master_fragment).navigateUp() || super.onSupportNavigateUp()
    }
}