package com.debian17.newstestapp.app.ui.news.adapter

import androidx.recyclerview.widget.DiffUtil
import com.debian17.newstestapp.data.model.Article

class NewsDiffCallback : DiffUtil.ItemCallback<Article>() {

    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.description == newItem.description
    }

}