package com.example.notesapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesapp.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
public abstract class NotesDatabase : RoomDatabase() {

    abstract fun getNoteDao(): NotesDao

    companion object {
        @Volatile
        private var INSTANCE: NotesDatabase? = null

        fun getDatabase(context: Context) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(context, NotesDatabase::class.java, "notes_database")
                .build().also {
                    INSTANCE = it
                }
        }

    }


}