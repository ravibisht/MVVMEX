package com.stark.mvvmex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class NotesRVAdapter(private val listener: INotesRVAdapter) :
    RecyclerView.Adapter<NotesRVAdapter.ViewHolder>() {

    private var allNotes = ArrayList<Note>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.note_item, parent, false)
        )
        viewHolder.delBT.setOnClickListener { listener.onItemClicked(allNotes[viewHolder.adapterPosition]) }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.noteTV.text = currentNote.text

    }

    override fun getItemCount(): Int = allNotes.size

    fun upateAllNotes(newNotes: List<Note>) {
        this.allNotes = newNotes as ArrayList<Note>
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteTV = itemView.findViewById<TextView>(R.id.note_TV)
        val delBT = itemView.findViewById<ImageView>(R.id.delBT)
    }
}

interface INotesRVAdapter {
    fun onItemClicked(note: Note)
}