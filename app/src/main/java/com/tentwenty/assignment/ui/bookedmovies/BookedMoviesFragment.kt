package com.tentwenty.assignment.ui.bookedmovies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tentwenty.assignment.R
import com.tentwenty.assignment.databinding.FragmentBookedMoviesBinding
import com.tentwenty.assignment.ui.gallery.UpcomingMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookedMoviesFragment : Fragment(R.layout.fragment_booked_movies) {

    private val viewModel: BookedMoviesViewModel by viewModels()
    private val upcomingViewModel: UpcomingMoviesViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentBookedMoviesBinding.bind(view)

        val taskAdapter = BookedMoviesAdapter()

        binding.apply {
            recyclerViewTasks.apply {
                adapter = taskAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

        viewModel.bookedMovies.observe(viewLifecycleOwner) {
            taskAdapter.submitList(it)
        }
    }
}