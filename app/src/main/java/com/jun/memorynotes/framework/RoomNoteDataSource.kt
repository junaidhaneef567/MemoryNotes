package com.jun.memorynotes.framework

import android.content.Context
import com.jun.core.data.Note
import com.jun.core.repository.NoteDataSource
import com.jun.memorynotes.framework.db.DatabaseService
import com.jun.memorynotes.framework.db.NoteEntity

class RoomNoteDataSource(context: Context): NoteDataSource {

    val noteDao= DatabaseService.getInstance(context).noteDao()

    override suspend fun add(note: Note) = noteDao.addNoteEntity(NoteEntity.fromNote(note))

    override suspend fun get(id: Long) = noteDao.getNoteEntity(id)?.toNote()

    override suspend fun getAll()= noteDao.getAllNoteEntity().map { it.toNote() }

    override suspend fun remove(note: Note) = noteDao.deleteNoteEntity(NoteEntity.fromNote(note))
}