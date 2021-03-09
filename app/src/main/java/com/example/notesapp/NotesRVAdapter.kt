package com.example.notesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.model.Note

class NotesRVAdapter(
    private val context: Context,
    private val listener: OnItemClickListener
) :
    RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {

    private val notes: ArrayList<Note> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_view, parent, false)
        val viewHolder = NoteViewHolder(view)
        viewHolder.noteDeleteBtn.setOnClickListener { listener.onNoteDelete(notes[viewHolder.adapterPosition]) }
        viewHolder.itemView.setOnClickListener { listener.onNoteClick(notes[viewHolder.adapterPosition]) }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.noteTextView.text = notes[position].text
        holder.itemView.setBackgroundColor(notes[position].color)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun updateNotes(notes: List<Note>) {
        this.notes.clear()
        this.notes.addAll(notes)

        notifyDataSetChanged()
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteTextView: TextView = itemView.findViewById(R.id.noteTv)
        val noteDeleteBtn: ImageView = itemView.findViewById(R.id.delBtn)
    }

    interface OnItemClickListener {
        fun onNoteDelete(note: Note)
        fun onNoteClick(note: Note)
    }
}
