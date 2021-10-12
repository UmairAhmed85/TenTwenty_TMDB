package com.tentwenty.assignment.ui.details


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.tentwenty.assignment.BuildConfig
import com.tentwenty.assignment.R
import com.tentwenty.assignment.data.movieimages.MovieImages
import com.tentwenty.assignment.databinding.ItemViewpagerBinding

class MovieImageViewPagerAdapter(imagesList: List<MovieImages>) :
    RecyclerView.Adapter<MovieImageViewPagerAdapter.MovieImageViewHolder>() {
    private val imagesList = imagesList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieImageViewHolder {
        val binding =
            ItemViewpagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieImageViewHolder(binding)
    }


    override fun getItemCount(): Int =
        if (imagesList.size < 5) imagesList.size else 5

    override fun onBindViewHolder(holder: MovieImageViewHolder, position: Int) {
        holder.bind(imagesList.get(position))
    }


    inner class MovieImageViewHolder(private val binding: ItemViewpagerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: MovieImages) {
            binding.apply {
                Glide.with(itemView)
                    .load(
                        BuildConfig.URL_IMAGE_SIZE_THUMBNAIIL+image.file_path
                    )
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error)
                    .into(imageView)
            }
        }
    }
}

