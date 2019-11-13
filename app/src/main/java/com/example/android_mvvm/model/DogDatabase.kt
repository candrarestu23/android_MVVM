package com.example.android_mvvm.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(DogBreed::class), version = 1) // it means we can only have DogBreed class data
abstract class DogDatabase: RoomDatabase() { // this is singleton
    abstract fun dogDao(): DogDao

    companion object {
        @Volatile private var instance: DogDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            DogDatabase::class.java,
            "dogdatabase"
        ).build()
    }
}