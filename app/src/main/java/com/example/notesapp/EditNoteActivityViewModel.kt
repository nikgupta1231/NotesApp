package com.example.notesapp

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notesapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditNoteActivityViewModel(application: Application) : AndroidViewModel(application) {

    val allNotes: LiveData<List<Note>>
    private val notesRepo: NotesRepository

    init {
        val notesDao = NotesDatabase.getDatabase(application).getNoteDao()
        notesRepo = NotesRepository(notesDao)

        allNotes = notesRepo.allNotesDao
    }

    fun addNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        notesRepo.insert(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        notesRepo.delete(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        Log.d("ViewModel", ": ${note.id} ${note.text}")
        notesRepo.insert(note)
    }


}