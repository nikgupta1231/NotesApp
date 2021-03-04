package com.example.notesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notesapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    val allNotes: LiveData<List<Note>>
    private val notesRepo: NotesRepository

    init {
        val notesDao = NotesDatabase.getDatabase(application).getNoteDao()
        notesRepo = NotesRepository(notesDao)

        allNotes = notesRepo.allNotesDao
    }

    fun addNote(note: String) = viewModelScope.launch(Dispatchers.IO) {
        notesRepo.insert(Note(note))
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        notesRepo.delete(note)
    }

}