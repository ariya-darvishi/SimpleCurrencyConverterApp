package com.example.simplecurrencyconverterapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.simplecurrencyconverterapp.databinding.ActivityMainBinding
import com.example.simplecurrencyconverterapp.main.MainViewModel
import com.example.simplecurrencyconverterapp.utils.CurrencyEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnConvert.setOnClickListener {
            viewModel.convert(
                binding.etFrom.text.toString(),
                binding.spFrom.selectedItem.toString(),
                binding.spTo.selectedItem.toString()
            )
        }

        lifecycleScope.launchWhenStarted {
            viewModel.conversion.collect { event ->
                when (event) {
                    is CurrencyEvent.Success -> {
                        binding.progressBar.isVisible = false
                        binding.tvResult.text = event.resultText
                    }
                    is CurrencyEvent.Failure -> {
                        binding.progressBar.isVisible = false
                        binding.tvResult.setTextColor(Color.RED)

                    }
                    is CurrencyEvent.Loading -> {
                        binding.progressBar.isVisible = true

                    }
                    else -> Unit
                }
            }
        }

    }
}