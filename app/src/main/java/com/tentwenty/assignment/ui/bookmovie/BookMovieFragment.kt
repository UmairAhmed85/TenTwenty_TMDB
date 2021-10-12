package com.tentwenty.assignment.ui.bookmovie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.tentwenty.assignment.R
import com.tentwenty.assignment.databinding.FragmentBookMovieBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BookMovieFragment : Fragment(R.layout.fragment_book_movie) {
    private val args by navArgs<BookMovieFragmentArgs>()
    private val viewModel: BookMovieViewModel by viewModels()
    private var _binding: FragmentBookMovieBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBookMovieBinding.bind(view)
        binding.apply {
            val bookUpComingMovie = args.upcomingMovie
            buttonSumit.setOnClickListener {

                val location = edittextLocation.text.toString()
                val cinema = edittextCinema.text.toString()
                val seatNumber: Int
                if (edittextSeatNumber.text.toString().isNotEmpty())
                    seatNumber = edittextSeatNumber.text.toString().toInt()
                else seatNumber = 0

                if (location.isBlank()) {
                    showSnackbarMessage("Location cannot be empty")
                    return@setOnClickListener
                }

                if (cinema.isBlank()) {
                    showSnackbarMessage("Cinema cannot be empty")
                    return@setOnClickListener
                }

                if (seatNumber < 1 || seatNumber > 80) {
                    showSnackbarMessage("Please select seat number in range of 1 - 80")
                    return@setOnClickListener
                }

                viewModel.onSaveClick(location, cinema, seatNumber, bookUpComingMovie.id,bookUpComingMovie.poster_path)
                showSnackbarMessage("Movie Booked Successfully")

                findNavController().popBackStack()
            }
        }
    }

    private fun showSnackbarMessage(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}