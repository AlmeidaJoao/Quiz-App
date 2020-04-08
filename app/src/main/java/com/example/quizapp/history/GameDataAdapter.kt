package com.example.quizapp.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.database.Game
import com.example.quizapp.databinding.GameListViewBinding


class GameDataAdapter: ListAdapter<Game, GameDataAdapter.ViewHolder>(
    GameDiffCallBack()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(
            parent
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
    class ViewHolder private constructor(val binding: GameListViewBinding):
            RecyclerView.ViewHolder(binding.root){
        fun bind(item: Game) {
            binding.game = item
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = GameListViewBinding.inflate(layoutInflater,
                parent, false)
                return ViewHolder(
                    binding
                )
            }
        }
    }

    class GameDiffCallBack: DiffUtil.ItemCallback<Game>() {
        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.gameId == newItem.gameId
        }

        override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem == newItem
        }

    }


}



