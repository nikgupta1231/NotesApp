package com.example.notesapp

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.notesapp.model.Note
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_edit_note.*

class EditNoteActivity : AppCompatActivity() {

    companion object {
        const val KEY_NOTE_ID    = "note_id"
        const val KEY_NOTE_COLOR = "note_color"
        const val KEY_NOTE_TEXT  = "note_text"
    }

    lateinit var viewModel: EditNoteActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)

        setSupportActionBar(findViewById(R.id.toolbar))
        back_btn.setOnClickListener { finish() }

        val noteId = intent.getIntExtra(KEY_NOTE_ID, 0)
        val noteText: String
        val noteColor: Int
        if (noteId != 0) {
            toolbar_header.text = getString(R.string.edit_note)

            noteText = intent.getStringExtra(KEY_NOTE_TEXT) ?: ""
            noteColor = intent.getIntExtra(KEY_NOTE_COLOR, 0)

            note_edt.setText(noteText)
            note_edt.setSelection(noteText.length)
        } else {
            toolbar_header.text = getString(R.string.add_note)
            noteColor = Utils.getRandomColor(this)
        }

        parent_layout.setBackgroundColor(noteColor)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(EditNoteActivityViewModel::class.java)

        done_btn.setOnClickListener {
            if (noteId == 0) addNote(noteColor)
            else updateNote(noteId, noteColor)
        }
    }

    private fun updateNote(noteId: Int, noteColor: Int) {
        val msg = note_edt.text.toString()
        if (msg.isNotEmpty()) {
            val note = Note(msg)
            note.color = noteColor
            note.id = noteId
            viewModel.updateNote(note)
            finish()
        } else {
            showSnackbar(noteColor)
        }
    }

    private fun addNote(noteColor: Int) {
        val msg = note_edt.text.toString()
        if (msg.isNotEmpty()) {
            val note = Note(msg)
            note.color = noteColor
            viewModel.addNote(note)
            finish()
        } else {
            showSnackbar(noteColor)
        }
    }

    private fun showSnackbar(noteColor: Int) {
        Snackbar.make(
            parent_layout,
            "Cannot save empty note!",
            Snackbar.LENGTH_LONG
        )
            .setBackgroundTint(noteColor)
            .setTextColor(Color.BLACK)
            .show()
    }

}