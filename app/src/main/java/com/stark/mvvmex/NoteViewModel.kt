package com.stark.mvvmex

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    val allNotes: LiveData<List<Note>>
    val repository: NoteRepository

    init {
        val dbRepo = NoteRepository(NoteDatabase.getDatabase(application).noteDAO())
        allNotes = dbRepo.allNotes
        repository = NoteRepository(NoteDatabase.getDatabase(application).noteDAO())
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun insertNote(note: Note) = viewModelScope.launch (Dispatchers.IO){
        repository.insert(note)
    }
}
