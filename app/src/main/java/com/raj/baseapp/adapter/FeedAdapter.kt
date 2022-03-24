package com.raj.baseapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raj.baseapp.api.Feed
import com.raj.baseapp.databinding.ListItemBinding

class FeedAdapter(val listener: () -> Unit) :
    RecyclerView.Adapter<FeedAdapter.ItemViewHolder>() {
    class ItemViewHolder(private val listItemBinding: ListItemBinding) :
        RecyclerView.ViewHolder(listItemBinding.root) {
        fun bind(feed: Feed) {
            listItemBinding.author.text = feed.author
            listItemBinding.body.text = feed.body
            Glide.with(listItemBinding.root.context).load(feed.image.url)
                .into(listItemBinding.imageView)

        }
    }

    private val _feeds = ArrayList<Feed>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(_feeds[position])

        if (position == _feeds.size - 1) {
            Log.d(TAG, "onBindViewHolder: position->$position")
            Log.d(TAG, "onBindViewHolder: _feedsSize->${_feeds.size - 1}")
            listener()
        }
    }

    override fun getItemCount(): Int {
        return _feeds.size
    }

    fun updateData(feeds: List<Feed>) {
//        _feeds.clear()
        _feeds.addAll(feeds)
        notifyDataSetChanged()

    }

    companion object {
        const val TAG = "FeedAdapter"
    }
}