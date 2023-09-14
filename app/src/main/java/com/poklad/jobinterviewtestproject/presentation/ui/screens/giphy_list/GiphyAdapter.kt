package com.poklad.jobinterviewtestproject.presentation.ui.screens.giphy_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.poklad.jobinterviewtestproject.R
import com.poklad.jobinterviewtestproject.data.model.GifItem
import com.poklad.jobinterviewtestproject.databinding.ItemGiphyBinding
import com.poklad.jobinterviewtestproject.presentation.ui.base.BaseAdapter

class GiphyAdapter : BaseAdapter<GifItem>() {

    private val differCallback = object : DiffUtil.ItemCallback<GifItem>() {
        override fun areItemsTheSame(oldItem: GifItem, newItem: GifItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GifItem, newItem: GifItem): Boolean {
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
        RecyclerView.ViewHolder(binding.root), Binder<GifItem> {
        override fun bind(item: GifItem) {
            binding.apply {
                Glide.with(itemView.context)
                    .asGif()
                    .load(item.images.original.url)
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