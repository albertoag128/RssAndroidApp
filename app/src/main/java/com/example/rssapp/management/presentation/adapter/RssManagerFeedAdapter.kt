package com.example.rssapp.management.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rssapp.R
import com.example.rssapp.management.domain.UserRss

class RssManagerFeedAdapter : RecyclerView.Adapter<RssManagerFeedViewHolder>(){

    var dataSet = mutableListOf<UserRss>()

    fun setDataItems(rss: List<UserRss>){
        dataSet.clear()
        dataSet.addAll(rss)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RssManagerFeedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_rss_item_fragment, parent, false)
        return RssManagerFeedViewHolder(view)
    }

    override fun onBindViewHolder(holder: RssManagerFeedViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}