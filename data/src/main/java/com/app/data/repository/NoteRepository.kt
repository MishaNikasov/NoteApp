package com.app.data.repository

import com.app.domain.model.Note
import com.app.domain.repository.NoteRepository
import com.app.util.DataState
import java.util.*
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(): NoteRepository {
    override suspend fun getNoteList(): DataState<List<Note>> {
        return DataState.successes(listOf(
            Note(
                id = "123",
                title = "Title",
                text = "Qwqe ewqeqw ewqe qwe wq eqw e qw eqw ew",
                createDate = Date(),
                editDate = Date()
            ),
            Note(
                id = "123",
                title = "Title2",
                text = "123Q1wqe ewqeqw ewqe qwe wq eqw e qw eqw ew123Q1wqe ewqeqw ewqe qwe wq eqw e qw eqw ew123Q1wqe ewqeqw ewqe qwe wq eqw e qw eqw ew123Q1wqe ewqeqw ewqe qwe wq eqw e qw eqw ew123Q1wqe ewqeqw ewqe qwe wq eqw e qw eqw ew123Q1wqe ewqeqw ewqe qwe wq eqw e qw eqw ew123Q1wqe ewqeqw ewqe qwe wq eqw e qw eqw ew123Q1wqe ewqeqw ewqe qwe wq eqw e qw eqw ew123Q1wqe ewqeqw ewqe qwe wq eqw e qw eqw ew123Q1wqe ewqeqw ewqe qwe wq eqw e qw eqw ew123Q1wqe ewqeqw ewqe qwe wq eqw e qw eqw ew123Q1wqe ewqeqw ewqe qwe wq eqw e qw eqw ew123Q1wqe ewqeqw ewqe qwe wq eqw e qw eqw ew123Q1wqe ewqeqw ewqe qwe wq eqw e qw eqw ew123Q1wqe ewqeqw ewqe qwe wq eqw e qw eqw ew123Q1wqe ewqeqw ewqe qwe wq eqw e qw eqw ew123Q1wqe ewqeqw ewqe qwe wq eqw e qw eqw ew123Q1wqe ewqeqw ewqe qwe wq eqw e qw eqw ew",
                createDate = Date(),
                editDate = Date()
            )
        ))
    }
}