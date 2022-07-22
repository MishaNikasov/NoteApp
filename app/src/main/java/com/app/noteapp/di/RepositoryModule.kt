package com.app.noteapp.di

import com.app.data.mapper.NoteMapper
import com.app.data.repository.NoteRepositoryImpl
import com.app.data.room.dao.NoteDao
import com.app.domain.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideNoteRepository(
        noteDao: NoteDao,
        noteMapper: NoteMapper
    ): NoteRepository = NoteRepositoryImpl(noteDao, noteMapper)

}