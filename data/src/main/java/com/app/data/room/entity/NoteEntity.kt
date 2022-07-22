package com.app.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String,
    val text: String,
    @ColumnInfo(name = "create_date") val createDate: Long,
    @ColumnInfo(name = "edit_date") val editDate: Long?
)