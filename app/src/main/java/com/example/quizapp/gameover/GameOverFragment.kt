package com.example.quizapp.gameover

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.example.quizapp.R
import com.example.quizapp.database.GameDatabase
import com.example.quizapp.databinding.FragmentGameoverBinding

/**
 * A simple [Fragment] subclass.
 */
class GameOverFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentGameoverBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_gameover,
            container,
            false)

        setHasOptionsMenu(true)

        val arguments = GameOverFragmentArgs.fromBundle(requireArguments())

        val application = requireNotNull(this.activity).application

        val database = GameDatabase.getInstance(application).gameDao

        val gameOverFragmentViewModelFactory = GameOverFragmentViewModelFactory(
            database, arguments.score, arguments.playerName)

        val gameOverFragmentViewModel = ViewModelProvider(
            this, gameOverFragmentViewModelFactory).get(GameOverFragmentViewModel::class.java)

        binding.gameOverFragmentViewModel = gameOverFragmentViewModel

        gameOverFragmentViewModel.saveData()

        binding.button3.setOnClickListener {
            val action = GameOverFragmentDirections.actionGameOverFragmentToTitleFragment("")
            this.findNavController(). navigate(action)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, this.findNavController()) ||
                super.onOptionsItemSelected(item)

    }
}
