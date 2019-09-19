package com.debian17.newstestapp.app.ui.news.adapter

import android.content.Context
import android.graphics.Paint
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.debian17.newstestapp.R
import com.debian17.newstestapp.app.base.recycler.PaginationAdapter
import com.debian17.newstestapp.data.model.Article

class NewsAdapter(
    context: Context,
    diffCallback: NewsDiffCallback,
    private val urlClickListener: UrlClickListener
) :
    PaginationAdapter<Article>(context, diffCallback) {

    interface UrlClickListener {
        fun onUrlCLick(url: String)
    }

    private val requestOptions = RequestOptions()
        .placeholder(R.mipmap.ic_launcher)
        .error(R.mipmap.ic_launcher)

    override fun getViewTypeForItem(position: Int): Int {
        return R.layout.item_article
    }

    override fun onCreateViewHolderForItem(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view = layoutInflater.inflate(R.layout.item_article, parent, false)
        val viewHolder = NewsViewHolder(view)
        viewHolder.tvUrl.setOnClickListener {
            val article = getItem(viewHolder.adapterPosition)
            if (article?.url != null) {
                urlClickListener.onUrlCLick(article.url)
            }
        }
        return viewHolder
    }

    override fun onBindViewHolderForItem(holder: RecyclerView.ViewHolder, position: Int) {
        val article = getItem(position)
        if (article != null && holder is NewsViewHolder) {
            holder.bind(article, requestOptions)
        }
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val ivImage: ImageView = itemView.findViewById(R.id.ivImage)
        private val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        val tvUrl: TextView = itemView.findViewById(R.id.tvUrl)

        fun bind(article: Article, requestOptions: RequestOptions) {
            tvTitle.text = article.title
            Glide.with(ivImage)
                .applyDefaultRequestOptions(requestOptions)
                .load(article.image)
                .into(ivImage)
            tvDescription.text = article.description

            tvUrl.paintFlags = Paint.UNDERLINE_TEXT_FLAG
            tvUrl.text = article.url
        }

    }

}