package com.yychun1217.mytask.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yychun1217.mytask.databinding.ActivityDeliveryListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeliveryListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeliveryListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeliveryListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
    }
}