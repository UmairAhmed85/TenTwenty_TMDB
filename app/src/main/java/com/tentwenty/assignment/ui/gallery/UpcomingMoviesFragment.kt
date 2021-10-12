package com.tentwenty.assignment.ui.gallery

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.tentwenty.assignment.R
import com.tentwenty.assignment.data.genres.Genres
import com.tentwenty.assignment.data.upcomingmovies.UpcomingMovie
import com.tentwenty.assignment.databinding.FragmentUpcomingMoviesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpcomingMoviesFragment : Fragment(R.layout.fragment_upcoming_movies),
    UpcomingMoviesAdapter.OnItemClickListener {
companion object{
     var genresList: List<Genres> = listOf()
}
    private val viewModel by viewModels<UpcomingMoviesViewModel>()

    private var _binding: FragmentUpcomingMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentUpcomingMoviesBinding.bind(view)

        val adapter = UpcomingMoviesAdapter(this)

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.itemAnimator = null
            recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
                header = UpcomingMoviesLoadStateAdapter { adapter.retry() },
                footer = UpcomingMoviesLoadStateAdapter { adapter.retry() }
            )
            buttonRetry.setOnClickListener { adapter.retry() }
        }

        viewModel.upcomingMovies.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
        viewModel.genresList.observe(viewLifecycleOwner) {
            genresList = it.data!!
        }

        adapter.addLoadStateListener { loadState ->
            binding.apply {
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                recyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
                buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
                textViewError.isVisible = loadState.source.refresh is LoadState.Error

                // empty view
                if (loadState.source.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    adapter.itemCount < 1
                ) {
                    recyclerView.isVisible = false
                    textViewEmpty.isVisible = true
                } else {
                    textViewEmpty.isVisible = false
                }
            }
        }
    }

    override fun onItemClick(upcomingMovie: UpcomingMovie) {
        val action = UpcomingMoviesFragmentDirections.actionGalleryFragmentToDetailsFragment(upcomingMovie)
        findNavController().navigate(action)
    }

    override fun onItemBooked(upcomingMovie: UpcomingMovie) {
        val action = UpcomingMoviesFragmentDirections.actionGalleryFragmentToMovieBookingFragment(upcomingMovie)
        findNavController().navigate(action)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}