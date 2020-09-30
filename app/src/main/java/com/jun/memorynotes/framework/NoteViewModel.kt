package com.jun.memorynotes.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.jun.core.data.Note
import com.jun.core.repository.NoteRepository
import com.jun.core.usecase.AddNote
import com.jun.core.usecase.GetAllNotes
import com.jun.core.usecase.GetNote
import com.jun.core.usecase.RemoveNote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    private val coroutineScope= CoroutineScope(Dispatchers.IO)

    val repository = NoteRepository(RoomNoteDataSource(application))


    val useCases = UseCases(
        AddNote(repository),
        GetAllNotes(repository),
        GetNote(repository),
        RemoveNote(repository)
    )

    val saved = MutableLiveData<Boolean>()

    fun savedNote(note:Note){
        coroutineScope.launch {

            useCases.addNote(note)
            saved.postValue(true)

        }
    }
}