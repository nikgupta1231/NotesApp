package com.example.notesapp

import androidx.lifecycle.LiveData
import com.example.notesapp.model.Note

class NotesRepository(private val notesDao: NotesDao) {

    val allNotesDao: LiveData<List<Note>> = notesDao.getAllNotes()

    suspend fun insert(note: Note) {
        notesDao.insertNote(note)
    }

    suspend fun delete(note: Note) {
        notesDao.deleteNote(note)
    }
}