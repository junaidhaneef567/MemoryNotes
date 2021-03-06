package com.jun.core.usecase

import com.jun.core.data.Note
import com.jun.core.repository.NoteRepository

class AddNote(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(note: Note) = noteRepository.addNote(note)
}