package com.example.quizapp.gameover

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizapp.database.Game
import com.example.quizapp.database.GameDao
import kotlinx.coroutines.*

class GameOverFragmentViewModel(
    private val database: GameDao,
    val score:Int, val player: String): ViewModel() {

    private val _finalScore =  MutableLiveData<Int>()
    val finalScore: LiveData<Int>
        get() = _finalScore

    private val _playerName =  MutableLiveData<String>()
    val playerName: LiveData<String>
        get() = _playerName


    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    init{
        _finalScore.value = score
        _playerName.value = player
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun saveData() {
        uiScope.launch {
            val newGame = Game(gameScore = _finalScore.value!!, playerName = _playerName.value!!)
            insert(newGame)
        }
    }

    private suspend fun insert(game: Game) {
        withContext(Dispatchers.IO) {
            database.insert(game)
        }
    }


}