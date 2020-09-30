package com.jun.core.usecase

import com.jun.core.repository.NoteRepository

class GetNote(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(id: Long)= noteRepository.getNote(id)
}