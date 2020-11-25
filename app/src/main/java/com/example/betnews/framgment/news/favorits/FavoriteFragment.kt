package com.example.betnews.framgment.news.favorits

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.betnews.R
import com.example.betnews.base.BaseVMFragment
import com.example.betnews.data.locale.db.entity.NewsEntity
import com.example.betnews.framgment.news.DetailsFragment
import com.example.betnews.framgment.news.NewsViewModel
import com.example.betnews.framgment.news.adapter.NewsRecyclerAdapter
import com.example.betnews.utils.FragmentTag
import com.example.betnews.utils.open
import kotlinx.android.synthetic.main.fragment_news.*

class FavoriteFragment : BaseVMFragment<NewsViewModel>() {

    override val layoutResId = R.layout.fragment_news

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFavoriteNews()
        news_recycler.layoutManager = LinearLayoutManager(requireActivity())
        val adapter = NewsRecyclerAdapter({ viewModel.makeFavorite(it) }) { openDetails(it) }
        news_recycler.adapter = adapter
        viewModel.favoriteLiveData.observe(viewLifecycleOwner, Observer {
            empty_text.visibility = if (it.isEmpty()) {
                View.VISIBLE
            } else View.GONE
            adapter.submitList(it)
        })
    }

    private fun openDetails(it: NewsEntity) {
        sharedViewModel.newsData.value = it
        DetailsFragment().open(
            fragmentManager = parentFragmentManager,
            tag = FragmentTag.DETAIL_FRAGMENT
        )
    }
}