package com.example.note_app

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    private var noteRepository: NoteRepository
    val allNotes: LiveData<List<Note>>

    init {
        val noteDao: NoteDao = NoteDatabase.getDatabase(application).getNoteDao()

        noteRepository = NoteRepository(noteDao)
        allNotes = noteRepository.allNotes
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO){
        noteRepository.delete(note)
    }

    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        noteRepository.insert(note)
    }
}