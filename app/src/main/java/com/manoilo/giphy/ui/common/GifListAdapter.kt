package com.manoilo.giphy.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.manoilo.giphy.R
import com.manoilo.giphy.databinding.GifItemBinding
import com.manoilo.giphy.entity.Gif
import com.manoilo.giphy.utils.AppExecutors

class GifListAdapter(
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors
) : DataBoundListAdapter<Gif, GifItemBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<Gif>() {
        override fun areItemsTheSame(oldItem: Gif, newItem: Gif): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Gif, newItem: Gif): Boolean {
            return oldItem.url == newItem.url
        }
    }
) {

    override fun createBinding(parent: ViewGroup): GifItemBinding {
        val binding = DataBindingUtil.inflate<GifItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.gif_item,
            parent,
            false,
            dataBindingComponent
        )
        return binding
    }

    override fun bind(binding: GifItemBinding, item: Gif) {
        binding.gif = item
    }
}
