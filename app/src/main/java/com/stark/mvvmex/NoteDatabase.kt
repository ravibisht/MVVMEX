package com.stark.mvvmex

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "word_database";

        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {
             return INSTANCE ?: synchronized(this) {
                 val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    DATABASE_NAME
                ).build()

                INSTANCE = instance
                INSTANCE!!
            }
        }
    }

    abstract fun noteDAO(): NoteDAO
}