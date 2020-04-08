package com.example.quizapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_history_table")
data class Game(
    @PrimaryKey(autoGenerate = true) val gameId: Long = 0L,
    @ColumnInfo(name = "start_time") val gameStartMilli: Long = System.currentTimeMillis(),
    @ColumnInfo(name = "score") val gameScore: Int = 0,
    @ColumnInfo(name = "player_name") val playerName: String = "")