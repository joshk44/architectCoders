package com.joseferreyra.architectcoders.main.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joseferreyra.architectcoders.R
import com.joseferreyra.architectcoders.databinding.ItemUserNoteBinding

class NotesAdapter(var notes: List<String>) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NotesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user_note, parent, false)
        )

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.onBind(notes[position])
    }

    fun updateNotesContent(notes: List<String>) {
        this.notes = notes
    }

    override fun getItemCount() = notes.size

    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemUserNoteBinding.bind(itemView)

        fun onBind(note: String) {
            binding.tvNoteContent.text = note
        }
    }
}