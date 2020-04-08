package com.example.quizapp.history

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.database.GameDao
import java.lang.IllegalArgumentException

class GameHistoryFragmentViewModelFactory(
    val database: GameDao,
    private val application: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(GameHistoryFragmentViewModel::class.java)) {
            return GameHistoryFragmentViewModel(database, application) as T
        }
        throw IllegalArgumentException("")
    }

}