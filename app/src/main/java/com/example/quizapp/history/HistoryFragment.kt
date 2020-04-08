package com.example.quizapp.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.R
import com.example.quizapp.database.GameDatabase
import com.example.quizapp.databinding.FragmentHistoryBinding

/**
 * A simple [Fragment] subclass.
 */
class HistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentHistoryBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_history,
            container,
            false)

        val application = requireNotNull(this.activity).application

        val database = GameDatabase.getInstance(application)!!.gameDao

        val gameHistoryFragmentFactory = GameHistoryFragmentViewModelFactory(database, application)

        val gameHistoryFragmentViewModel = ViewModelProvider(this, gameHistoryFragmentFactory)
            .get(GameHistoryFragmentViewModel::class.java)

        binding.gameHistoryFragmentViewModel = gameHistoryFragmentViewModel

        binding.lifecycleOwner = this

        val adapter = GameDataAdapter()

        binding.recyclerView.adapter = adapter

        gameHistoryFragmentViewModel.games.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        return binding.root
    }
}
