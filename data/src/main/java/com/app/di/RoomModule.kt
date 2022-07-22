package com.app.di

import android.content.Context
import androidx.room.Room
import com.app.data.room.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATABASE_NAME = "notes_db"

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext applicationContext: Context) = Room.databaseBuilder(
        applicationContext,
        NoteDatabase::class.java,
        DATABASE_NAME
    ).build()


    @Provides
    @Singleton
    fun provideNoteDao(room: NoteDatabase) = room.noteDao()
}