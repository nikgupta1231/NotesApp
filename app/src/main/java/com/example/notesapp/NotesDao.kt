package com.example.notesapp

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notesapp.model.Note

@Dao
interface NotesDao {

    @Query("SELECT * from notes_table ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<Note>> //making it live data because our notes me be added or deleted(changes at runtime)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(note: Note)      //added suspend keyword because it blocks UI/Main thread

    @Delete
    suspend fun deleteNode(note: Note)         //added suspend keyword because it blocks UI/Main thread

}