package com.app.noteapp.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class NoteScreenType : Parcelable {
    @Parcelize
    object Create : NoteScreenType()
    @Parcelize
    data class Edit(val noteId: Long) : NoteScreenType()
}