package com.example.betnews.framgment.news

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.betnews.R
import com.example.betnews.base.BaseVMFragment
import com.example.betnews.base.pagination.PaginationScrollListener
import com.example.betnews.data.locale.db.entity.NewsEntity
import com.example.betnews.framgment.news.adapter.NewsRecyclerAdapter
import com.example.betnews.utils.FragmentTag
import com.example.betnews.utils.open
import kotlinx.android.synthetic.main.fragment_news.*

private const val START_PAGE = 1
private const val PAGE_SIZE = 25

class NewsFragment : BaseVMFragment<NewsViewModel>() {

    private lateinit var newsAdapter: NewsRecyclerAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private var isLoading: Boolean = false
    private var isLastPage: Boolean = false

    override val layoutResId = R.layout.fragment_news

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getNews(START_PAGE, PAGE_SIZE)
        handleNewsData()
        layoutManager = LinearLayoutManager(requireActivity())
        news_recycler.layoutManager = layoutManager
        newsAdapter = NewsRecyclerAdapter({ viewModel.makeFavorite(it) }) { openDetails(it) }
        news_recycler.adapter = newsAdapter
    }

    private fun openDetails(it: NewsEntity) {
        sharedViewModel.newsData.value = it
        DetailsFragment().open(
            fragmentManager = parentFragmentManager,
            tag = FragmentTag.DETAIL_FRAGMENT
        )
    }

    private fun handleNewsData() {
        viewModel.responseLiveData.observe(viewLifecycleOwner, Observer {
            var nextPage = it.currentPage
            isLoading = false
            connectPaging(it.currentPage, ++nextPage, it.total)
            hideProgress()
        })

        viewModel.resultLiveData.observe(viewLifecycleOwner, Observer {
            empty_text.visibility = if (it.isEmpty()) {
                View.VISIBLE
            } else View.GONE
            newsAdapter.submitList(it)
        })
    }

    private fun connectPaging(currentPage: Int, nextPage: Int, totalPages: Int) {
        news_recycler.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun loadMoreItems() {
                isLastPage = currentPage == totalPages
                if (!isLastPage) {
                    showProgress()
                    viewModel.getNews(nextPage, PAGE_SIZE)
                    isLoading = true
                }
            }

            override fun isLastPage() = isLastPage

            override fun isLoading() = isLoading
        })
    }

    private fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        progress.visibility = View.GONE
    }
}