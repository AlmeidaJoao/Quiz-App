package com.example.quizapp.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentGameBinding

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentGameBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_game, container,false)

        val  gameFragmentViewModel = ViewModelProvider(this)
            .get(GameFragmentViewModel::class.java)

        val arguments = GameFragmentArgs.fromBundle(requireArguments())

        binding.gameFragmentViewModel = gameFragmentViewModel

        binding.lifecycleOwner = this

        gameFragmentViewModel.randomizeQuestion()

        gameFragmentViewModel.navigateToGameOver.observe(this.viewLifecycleOwner, Observer {
            if (it == true) {
                val action = GameFragmentDirections
                    .actionGameFragmentToGameOverFragment(
                        gameFragmentViewModel.score,
                        arguments.playerName)

                this.findNavController().navigate(action)

                gameFragmentViewModel.doneNavigating()
            }
        })

        return binding.root;
    }
}