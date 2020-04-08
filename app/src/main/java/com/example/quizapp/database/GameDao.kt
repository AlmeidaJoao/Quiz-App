package com.example.quizapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GameDao{
    @Insert
    fun insert(game: Game)

    @Query("SELECT* FROM game_history_table ORDER BY gameId DESC")
    fun getAll(): LiveData<List<Game>>
}