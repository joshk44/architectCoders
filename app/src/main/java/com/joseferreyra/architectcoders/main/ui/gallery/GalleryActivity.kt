package com.joseferreyra.architectcoders.main.ui.gallery

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.joseferreyra.architectcoders.databinding.ActivityGallleryBinding
import com.joseferreyra.architectcoders.main.ui.CirclePagerIndicatorDecoration

const val GALLERY_URLS = "gallery_urls"

class GalleryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val images = intent?.getStringArrayExtra(GALLERY_URLS)

        ActivityGallleryBinding.inflate(layoutInflater).also { binding ->
            setContentView(binding.root)
            images?.let { images ->
                setupGalleryView(binding, images)
            }
            getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupGalleryView(binding: ActivityGallleryBinding, images: Array<out String>) {
        binding.rvGallery.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.rvGallery)
        binding.rvGallery.adapter = GalleryAdapter(images.toList())
        binding.rvGallery.addItemDecoration(CirclePagerIndicatorDecoration())
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}