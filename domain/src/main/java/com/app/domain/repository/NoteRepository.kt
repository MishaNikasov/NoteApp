package com.app.domain.repository

import com.app.domain.model.Note
import com.app.util.DataState

interface NoteRepository {
    suspend fun getNoteList(): DataState<List<Note>>
    suspend fun getNote(id: Long): Note
    suspend fun insertNote(note: Note)
    suspend fun updateNote(note: Note)
    suspend fun deleteNote(id: Long)
}