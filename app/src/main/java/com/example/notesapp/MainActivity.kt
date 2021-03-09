package com.example.notesapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.model.Note
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NotesRVAdapter.OnItemClickListener {

    lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this, this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(MainActivityViewModel::class.java)
        viewModel.allNotes.observe(this, Observer { list ->
            list.let { adapter.updateNotes(it) }
        })

        floatingActionButton.setOnClickListener {
            intent = Intent(this, EditNoteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onNoteDelete(note: Note) {
        viewModel.deleteNote(note)
    }

    override fun onNoteClick(note: Note) {
        intent = Intent(this, EditNoteActivity::class.java)
        intent.putExtra(EditNoteActivity.KEY_NOTE_ID, note.id)
        intent.putExtra(EditNoteActivity.KEY_NOTE_COLOR, note.color)
        intent.putExtra(EditNoteActivity.KEY_NOTE_TEXT, note.text)
        startActivity(intent)
    }

}