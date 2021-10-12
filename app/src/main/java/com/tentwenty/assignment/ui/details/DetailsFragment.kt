package com.tentwenty.assignment.ui.details

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.tentwenty.assignment.R
import com.tentwenty.assignment.data.movieimages.MovieImages
import com.tentwenty.assignment.databinding.FragmentDetailsBinding
import com.tentwenty.assignment.ui.gallery.UpcomingMoviesFragment.Companion.genresList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val args by navArgs<DetailsFragmentArgs>()
    private val viewModel by viewModels<DetailViewModel>()
    var listOfImages: MutableList<MovieImages> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailsBinding.bind(view)

        binding.apply {
            val upcomingMovie = args.upcomingMovie
            viewModel.imageList.observe(viewLifecycleOwner) {
                when {
                    !it.isEmpty() -> {
                        viewPager.adapter = MovieImageViewPagerAdapter(it)
                        viewPager.offscreenPageLimit = 5
                        progressBar.isVisible = false
                    }
                    else -> {
                        progressBar.isVisible = true
                    }
                }
            }
            viewModel.getAllMovies(upcomingMovie.id)
            textViewTitle.text = upcomingMovie.title
            val upcomingMovieGenresListFiltered =
                upcomingMovie.genre_ids.flatMap { genresFilteredList -> genresList.filter { genresFilteredList == it.id } }
            textViewGenresValues.text = upcomingMovieGenresListFiltered.joinToString { it.name }
            textViewDateValue.text = upcomingMovie.release_date
            textViewOverviewValue.text = upcomingMovie.overview ?: "Description not available"
            textViewRatingValue.text = upcomingMovie.vote_average.toString()
            textViewTitle.isVisible = true
            textViewWatchTrailer.isVisible = true
            textViewDateHeading.isVisible = true
            textViewDateValue.isVisible = true
            textViewGenresHeading.isVisible = true
            textViewGenresValues.isVisible = true
            textViewOverviewHeading.isVisible = true
            textViewOverviewValue.isVisible = true
            textViewRatingHeading.isVisible = true
            textViewRatingValue.isVisible = true
        }
    }
}