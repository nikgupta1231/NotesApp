package com.example.notesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.model.Note
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NotesRVAdapter.OnItemClickListener {

    lateinit var viewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submit_button.setOnClickListener { addNote() }

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this, this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NotesViewModel::class.java)
        viewModel.allNotes.observe(this, Observer { list ->
            list.let { adapter.updateNotes(it) }
        })
    }

    private fun addNote() {
        val msg = note_edit_text.text.toString()
        if (msg.isNotEmpty()) {
            viewModel.addNote(
                msg
            )
        }
    }

    override fun onNoteDelete(note: Note) {
        viewModel.deleteNote(
            note
        )
    }
}