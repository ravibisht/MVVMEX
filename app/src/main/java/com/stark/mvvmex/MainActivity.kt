package com.stark.mvvmex

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.stark.mvvmex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), INotesRVAdapter {

    lateinit var noteViewModel: NoteViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NotesRVAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = NotesRVAdapter(this)

        noteViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory
                .getInstance(application)
        ).get(NoteViewModel::class.java)

        noteViewModel.allNotes.observe(this, { list ->
            list?.let {
                adapter.upateAllNotes(it)
            }
        })
        binding.notesRV.layoutManager = LinearLayoutManager(this)
        binding.notesRV.adapter = adapter
    }

    override fun onItemClicked(note: Note) {
        noteViewModel.deleteNote(note)
        Toast.makeText(applicationContext, "Deleted", Toast.LENGTH_SHORT).show()
    }

    fun submitNote(view: View) {
        val inputData = binding.input.text.toString()
        if (inputData.isNotEmpty()) {
            noteViewModel.insertNote(Note(inputData))
            Toast.makeText(applicationContext, "$inputData inserted", Toast.LENGTH_SHORT).show()
        }
    }


}