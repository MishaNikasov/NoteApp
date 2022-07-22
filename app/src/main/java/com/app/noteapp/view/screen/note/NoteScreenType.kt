package com.app.noteapp.view.screen.note

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class NoteScreenType : Parcelable {
    @Parcelize
    object Create : NoteScreenType()
    @Parcelize
    data class Edit(val noteId: Long) : NoteScreenType()
}