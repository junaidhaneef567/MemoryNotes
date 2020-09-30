package com.jun.memorynotes.framework

import com.jun.core.usecase.AddNote
import com.jun.core.usecase.GetAllNotes
import com.jun.core.usecase.GetNote
import com.jun.core.usecase.RemoveNote

data class UseCases(
  val addNote: AddNote,
  val getAllNotes: GetAllNotes,
  val getNote: GetNote,
  val removeNote: RemoveNote
)