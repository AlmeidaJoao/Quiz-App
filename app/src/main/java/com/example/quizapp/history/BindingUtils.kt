package com.example.quizapp.history

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.quizapp.R
import com.example.quizapp.database.Game

@BindingAdapter("setPlayerName")
fun TextView.setPlayerName(item: Game) {
    item.let {
        text = item.playerName
    }
}

@BindingAdapter("setGameTime")
fun TextView.setGameTime(item: Game) {
    item.let {
        text = item.gameStartMilli.toString()
    }
}

@BindingAdapter("setScoreImage")
fun ImageView.setImageResource(item: Game) {
    setImageResource(when(item.gameScore) {
        1 -> R.drawable.one
        2 -> R.drawable.two
        3 -> R.drawable.three
        4 -> R.drawable.four
        5 -> R.drawable.five
        6 -> R.drawable.six
        7 -> R.drawable.seven
        8 -> R.drawable.eight
        9 -> R.drawable.nine
        10 -> R.drawable.ten
        else -> R.drawable.ten
    })
}