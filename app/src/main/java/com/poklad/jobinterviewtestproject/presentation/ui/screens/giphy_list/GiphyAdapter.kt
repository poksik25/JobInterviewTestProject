package com.poklad.jobinterviewtestproject.presentation.ui.screens.giphy_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.poklad.jobinterviewtestproject.R
import com.poklad.jobinterviewtestproject.databinding.ItemGiphyBinding
import com.poklad.jobinterviewtestproject.presentation.model.GifItemPresentation
import com.poklad.jobinterviewtestproject.presentation.ui.base.BaseAdapter

class GiphyAdapter : BaseAdapter<GifItemPresentation>() {

    private val differCallback = object : DiffUtil.ItemCallback<GifItemPresentation>() {
        override fun areItemsTheSame(
            oldItem: GifItemPresentation,
            newItem: GifItemPresentation
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: GifItemPresentation,
            newItem: GifItemPresentation
        ): Boolean {
            return oldItem == newItem
        }
    }
    override val differList = AsyncListDiffer(this, differCallback)
    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemGiphyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GiphyHolder(binding)
    }

    inner class GiphyHolder(private val binding: ItemGiphyBinding) :
        RecyclerView.ViewHolder(binding.root), Binder<GifItemPresentation> {
        override fun bind(item: GifItemPresentation) {
            binding.apply {
                Glide.with(itemView.context)
                    .asGif()
                    .load(item.imageUrl)
                    .placeholder(R.drawable.pic_placeholder)
                    .centerCrop()
                    .into(ivGif)
                root.setOnClickListener {
                    onItemClickListener?.let { it(item) }
                }
            }
        }
    }
}