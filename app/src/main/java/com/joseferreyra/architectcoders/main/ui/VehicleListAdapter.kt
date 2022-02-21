package com.joseferreyra.architectcoders.main.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joseferreyra.architectcoders.R
import com.joseferreyra.architectcoders.databinding.ItemVehicleBinding
import com.joseferreyra.architectcoders.extensions.loadUrl

class VehicleListAdapter(
    var vehicles: List<VehicleUI>,
    private val onGalleryClick: (images: List<String>) -> Unit
) : RecyclerView.Adapter<VehicleListAdapter.VehicleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VehicleViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_vehicle, parent, false), onGalleryClick
        )

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        holder.onBind(vehicles[position])
    }

    fun updateDataSet(vehicleList: List<VehicleUI>) {
        vehicles = vehicleList
        notifyDataSetChanged()
    }

    override fun getItemCount() = vehicles.size

    class VehicleViewHolder(itemView: View, val onGalleryClick: (images: List<String>) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        private val binding = ItemVehicleBinding.bind(itemView)

        private val notesAdapter = NotesAdapter(emptyList())

        fun onBind(vehicle: VehicleUI) {
            binding.tvTitle.text = vehicle.title
            binding.tvFuelType.text = vehicle.fuel
            binding.tvPrice.text = "\$${vehicle.price}"

            setUpImagePreview(vehicle)

            setupNotes(vehicle)

        }

        private fun setupNotes(vehicle: VehicleUI) {
            if (vehicle.notes.isNotEmpty() == true) {
                binding.showNotes.visibility = View.VISIBLE
                binding.rvNotes.visibility = View.GONE
                notesAdapter.updateNotesContent(vehicle.notes)
                binding.rvNotes.adapter = notesAdapter
            } else {
                binding.showNotes.visibility = View.GONE
                binding.rvNotes.visibility = View.GONE
            }

            binding.btnReadNotes.setOnClickListener {
                binding.rvNotes.apply {
                    this.visibility = when (this.visibility) {
                        View.VISIBLE -> View.GONE
                        else -> View.VISIBLE
                    }
                }
            }
        }

        private fun setUpImagePreview(vehicle: VehicleUI) {
            if (vehicle.images.isNotEmpty() == true) {
                vehicle.images.get(0).let {
                    binding.ivVehicleImage.loadUrl(it)
                }
                binding.ivGallery.visibility = View.VISIBLE
                vehicle.images.let { images -> binding.ivGallery.setOnClickListener { onGalleryClick(images) } }
            } else {
                binding.ivGallery.visibility = View.GONE
            }
        }


    }
}