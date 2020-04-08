package com.example.quizapp.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import com.example.quizapp.database.GameDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class GameHistoryFragmentViewModel(
    private val database: GameDao,
    application: Application): AndroidViewModel(application) {

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val games = database.getAll()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}