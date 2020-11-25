package com.example.betnews.framgment.news.adapter

import android.view.View
import com.example.betnews.R
import com.example.betnews.base.adapter.BaseListAdapter
import com.example.betnews.data.locale.db.entity.NewsEntity
import com.example.betnews.utils.convertDate
import kotlinx.android.synthetic.main.news_item.view.*

class NewsRecyclerAdapter(
    val favoriteClick: (NewsEntity) -> Unit,
    val itemClicked: (NewsEntity) -> Unit
) : BaseListAdapter<NewsEntity>() {

    override val layoutResId = R.layout.news_item

    override fun onBind(itemView: View, item: NewsEntity, position: Int) {
        itemView.categoryTitleTextView.text = item.sectionName
        itemView.titleTextView.text = item.title
        itemView.dateTextView.text = item.publicationDate.convertDate()
        val favoriteImage = if (item.isFavorite) {
            R.drawable.ic_favorite_icon
        } else R.drawable.ic_unfavorite_icon

        itemView.favoriteIcon.setImageResource(favoriteImage)
        itemView.favoriteIcon.setOnClickListener {
            favoriteClick(item)
            notifyItemChanged(position)
        }
    }

    override fun onItemClick(item: NewsEntity) {
        itemClicked(item)
    }
}