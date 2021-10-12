package com.tentwenty.assignment.ui.booked

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tentwenty.assignment.R
import com.tentwenty.assignment.databinding.FragmentMoviesBookedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesBookedFragment : Fragment(R.layout.fragment_movies_booked) {

    private val viewModel by viewModels<MoviesBookedViewModel>()

    private var _binding: FragmentMoviesBookedBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMoviesBookedBinding.bind(view)


        binding.apply {

            buttonSumit.setOnClickListener {

            }
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_booking, menu)

        val searchItem = menu.findItem(R.id.action_booking)
        /*   searchItem.setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener {
      // Write action here to show booked movies  when done with
               return true
           })*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}