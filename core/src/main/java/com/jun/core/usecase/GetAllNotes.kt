package com.jun.core.usecase

import com.jun.core.repository.NoteRepository

class GetAllNotes(private val noteRepository: NoteRepository) {

    suspend operator fun invoke()=noteRepository.getAllNotes()
}