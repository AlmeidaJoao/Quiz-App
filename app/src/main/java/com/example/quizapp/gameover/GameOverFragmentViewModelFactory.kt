package com.example.quizapp.gameover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.database.GameDao

class GameOverFragmentViewModelFactory(val database: GameDao,
                                       val score: Int,val playerName: String): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(GameOverFragmentViewModel::class.java)) {
            return GameOverFragmentViewModel(database ,score, playerName) as T
        }
        throw IllegalArgumentException("Unknown view model")
    }

}