package com.app.data.mapper

import com.app.data.room.entity.NoteEntity
import com.app.domain.model.Note
import java.util.*
import javax.inject.Inject

class NoteMapper @Inject constructor(): Mapper<Note, NoteEntity> {

    override fun mapEntity(entity: NoteEntity): Note {
        return with(entity) {
            Note(
                id = id,
                title = title,
                text = text,
                createDate = Date(createDate),
                editDate = editDate?.let { Date(it) },
            )
        }
    }

    override fun mapModel(model: Note): NoteEntity {
        return with(model) {
            NoteEntity(
                id = id,
                title = title,
                text = text,
                createDate = createDate.time,
                editDate = editDate?.time,
            )
        }
    }

}