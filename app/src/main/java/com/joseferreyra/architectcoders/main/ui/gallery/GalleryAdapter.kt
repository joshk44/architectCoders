package com.joseferreyra.architectcoders.main.ui.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joseferreyra.architectcoders.extensions.loadUrl
import com.joseferreyra.architectcoders.R
import com.joseferreyra.architectcoders.databinding.ItemImageBinding

class GalleryAdapter(var imagesUrl: List<String>) : RecyclerView.Adapter<GalleryAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ImageViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_image, parent, false)
        )

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBind(imagesUrl[position])
    }

    override fun getItemCount() = imagesUrl.size

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemImageBinding.bind(itemView)

        fun onBind(image: String) {
            binding.imageView.loadUrl(image)
        }
    }
}