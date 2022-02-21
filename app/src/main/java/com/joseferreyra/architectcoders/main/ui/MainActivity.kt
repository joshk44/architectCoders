package com.joseferreyra.architectcoders.main.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.joseferreyra.architectcoders.R
import com.joseferreyra.architectcoders.databinding.ActivityMainBinding
import com.joseferreyra.architectcoders.main.ui.gallery.GALLERY_URLS
import com.joseferreyra.architectcoders.main.ui.gallery.GalleryActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModel<MainViewModel>()

    private lateinit var adapter: VehicleListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        observeViewModel()
    }

    private fun initViews() {
        binding.rvVehicles.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter = VehicleListAdapter(emptyList(), ::onGalleryClick).also {
            binding.rvVehicles.adapter = it
        }
        binding.pbLoading.visibility = View.VISIBLE
    }

    private fun onGalleryClick(list: List<String>) {
        startActivity(Intent(this, GalleryActivity::class.java).apply {
            putExtra(GALLERY_URLS, list.toTypedArray())
        })
    }

    private fun observeViewModel() {
        viewModel.vehicles.observe(this) { it?.let { updateVehicles(it) } }
        viewModel.onRequestError.observe(this) { onVehiclesError(it) }
        viewModel.requestVehicles()
    }

    private fun updateVehicles(vehiclesList: List<VehicleUI>) {
        if (vehiclesList.isNullOrEmpty()) return
        adapter.updateDataSet(vehiclesList)
        binding.pbLoading.visibility = View.GONE
    }

    private fun onVehiclesError(it: Boolean) {
        when (it) {
            true -> {
                Snackbar.make(binding.root, getString(R.string.error), Snackbar.LENGTH_INDEFINITE).show()
                binding.pbLoading.visibility = View.GONE
            }
            else -> Log.d("MainActivity", "onVehiclesError : no error")
        }
    }
}