package com.app.data.repository

import com.app.data.mapper.NoteMapper
import com.app.data.room.dao.NoteDao
import com.app.domain.model.Note
import com.app.domain.repository.NoteRepository
import com.app.util.DataState
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao,
    private val noteMapper: NoteMapper
): NoteRepository {

    override suspend fun getNoteList(): DataState<List<Note>> {
        return DataState.successes(
            noteMapper.mapEntityList(noteDao.getAll()).sortedByDescending { it.createDate }
        )
    }

    override suspend fun insertNote(note: Note) {
        noteDao.insertAll(noteMapper.mapModel(note))
    }

    override suspend fun updateNote(note: Note) {
        noteDao.updateNote(noteMapper.mapModel(note))
    }

    override suspend fun deleteNote(id: Long) {
        noteDao.deleteById(id)
    }

    override suspend fun getNote(id: Long): Note {
        return noteMapper.mapEntity(noteDao.findById(id))
    }

}