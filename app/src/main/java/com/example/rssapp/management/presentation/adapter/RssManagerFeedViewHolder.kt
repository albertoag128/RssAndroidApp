package com.example.rssapp.management.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.rssapp.databinding.UserRssItemFragmentBinding
import com.example.rssapp.management.domain.UserRss

class RssManagerFeedViewHolder (val view: View): RecyclerView.ViewHolder(view){
    fun bind(rss:UserRss){
        val binding = UserRssItemFragmentBinding.bind(view)
        binding.nombreRss.text = rss.name
        binding.urlRss.text = rss.url
    }
}