package com.tentwenty.assignment.ui.bookedmovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.tentwenty.assignment.BuildConfig
import com.tentwenty.assignment.R
import com.tentwenty.assignment.data.bookedmovies.BookedMovie
import com.tentwenty.assignment.databinding.ItemBookedMoviesBinding

class BookedMoviesAdapter() :
    ListAdapter<BookedMovie, BookedMoviesAdapter.TasksViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val binding =
            ItemBookedMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TasksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class TasksViewHolder(private val binding: ItemBookedMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: BookedMovie) {
            binding.apply {
                Glide.with(itemView)
                    .load(BuildConfig.URL_IMAGE_SIZE_THUMBNAIIL + task.movieImage)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(imageViewLogo)

                textViewLocation.text = "Location: " + task.location
                textViewCinema.text = "Cinema: " + task.cinema
                textViewSeatNumber.text = "Seat #: " + task.seat
                textViewTicketNumber.text = task.id.toString()
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<BookedMovie>() {
        override fun areItemsTheSame(oldItem: BookedMovie, newItem: BookedMovie) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: BookedMovie, newItem: BookedMovie) =
            oldItem == newItem
    }
}