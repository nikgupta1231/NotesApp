package com.example.notesapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Note(val text: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var color: Int = 0
}
