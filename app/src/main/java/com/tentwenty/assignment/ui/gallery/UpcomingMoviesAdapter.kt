package com.tentwenty.assignment.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.tentwenty.assignment.BuildConfig.URL_IMAGE_SIZE_THUMBNAIIL
import com.tentwenty.assignment.R
import com.tentwenty.assignment.data.upcomingmovies.UpcomingMovie
import com.tentwenty.assignment.databinding.UpcomingMovieItemBinding

class UpcomingMoviesAdapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<UpcomingMovie, UpcomingMoviesAdapter.PhotoViewHolder>(
        UPCOMING_MOVIES_COMPARATOR
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding =
            UpcomingMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class PhotoViewHolder(private val binding: UpcomingMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }
            }
            binding.textViewBook.setOnClickListener{
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemBooked(item)
                    }
                }
            }
        }

        fun bind(upcomingMovie: UpcomingMovie) {
            binding.apply {
                Glide.with(itemView)
                    .load(URL_IMAGE_SIZE_THUMBNAIIL+upcomingMovie.poster_path)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(imageViewLogo)

                textViewName.text = upcomingMovie.title
                textViewDate.text = upcomingMovie.release_date
                when (upcomingMovie.adult) {
                    true -> {
                        textViewType.text = "Adult"
                    }
                    else -> {
                        textViewType.text = "Non adult"
                    }
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(upcomingMovie: UpcomingMovie)
        fun onItemBooked(upcomingMovie: UpcomingMovie)
    }

    companion object {
        private val UPCOMING_MOVIES_COMPARATOR = object : DiffUtil.ItemCallback<UpcomingMovie>() {
            override fun areItemsTheSame(oldItem: UpcomingMovie, newItem: UpcomingMovie) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: UpcomingMovie, newItem: UpcomingMovie) =
                oldItem == newItem
        }
    }
}