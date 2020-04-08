package com.example.quizapp.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class Question(val text: String, val answers: List<String>)

class GameFragmentViewModel: ViewModel() {

    private val questions: MutableList<Question> = mutableListOf(
        Question("Qual e o nome do presidente de Mocambique",
            listOf("Nyusi", "Guebuza", "Samora","Chissano")),
        Question("Monte mais alto do mundo",
            listOf("Everest", "Binga", "Kilimanjaro", "Victoria"))
    )

    private val _answers = MutableLiveData<MutableList<String>>()

    val answers: LiveData<MutableList<String>>
        get() = _answers

    private var _currentQuestion = MutableLiveData<Question>()
    val currentQuestion: LiveData<Question>
        get() = _currentQuestion

    private val _navigateToGameOver = MutableLiveData<Boolean>()
    val navigateToGameOver: LiveData<Boolean>
       get() = _navigateToGameOver

    var nrQuestions: Int = questions.size
    var questionIndex: Int = -1
    private var selectedAnswerIndex = -1
    var score: Int = 0


    fun doneNavigating() {
        _navigateToGameOver.value = false;
    }

    fun randomizeQuestion() {
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }

    fun setQuestion() {
        _currentQuestion.value = questions[questionIndex]
        _answers.value = _currentQuestion.value!!.answers.toMutableList()
        _answers.value!!.shuffle()
    }

    fun getSelectedAnswerIndex(index: Int) {
        selectedAnswerIndex = index
    }

    fun moveOn() {
        questionIndex++
        if (questionIndex < nrQuestions) {
            _currentQuestion.value = questions[questionIndex]
            setQuestion()
        }
    }

    fun onNext(){
        if (selectedAnswerIndex != -1){

            if ( _answers.value?.get(selectedAnswerIndex) ==
                _currentQuestion.value?.answers?.get(0)) {
                moveOn()
                score++

            }
            else {
                moveOn()
                //score--
            }
            if(questionIndex >= nrQuestions) {
                _navigateToGameOver.value = true
            }
        }
    }

    fun onCancel() {
        _navigateToGameOver.value = true
    }

}