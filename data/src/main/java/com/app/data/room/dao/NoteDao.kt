package com.app.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.app.data.room.entity.NoteEntity

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    suspend fun getAll(): List<NoteEntity>

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun findById(id: Long): NoteEntity

    @Insert
    suspend fun insertAll(vararg notes: NoteEntity)

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Update
    suspend fun updateNote(id: NoteEntity)

}