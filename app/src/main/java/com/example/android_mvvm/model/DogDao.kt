package com.example.android_mvvm.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DogDao {
    @Insert
    suspend fun insertAll(vararg dogs: DogBreed): List<Long>

    @Query("SELECT * FROM dogbreed") //dogbreed is name of database that you get from model class name lower case
    suspend fun getAllDogs(): List<DogBreed>

    @Query("SELECT * FROM dogbreed WHERE uuid = :dogId")
    suspend fun getDog(dogId: Int): DogBreed // dogId is primary key

    @Query("DELETE FROM dogbreed")
    suspend fun deleteAlldogs()
}