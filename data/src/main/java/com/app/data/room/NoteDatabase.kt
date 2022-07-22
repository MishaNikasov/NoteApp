package com.app.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.data.room.dao.NoteDao
import com.app.data.room.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}