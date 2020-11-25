package com.example.betnews.framgment.news

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.betnews.R
import com.example.betnews.base.BaseFragment
import com.example.betnews.utils.convertDate
import kotlinx.android.synthetic.main.fragment_details.*

class DetailsFragment : BaseFragment() {

    override val layoutResId = R.layout.fragment_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.newsData.observe(viewLifecycleOwner, Observer {
            with(it) {
                categoryTextView.text = sectionName
                titleTextView.text = title
                dateTextView.text = publicationDate.convertDate()
                web_page.setOnClickListener {
                    val defaultBrowser = Intent.makeMainSelectorActivity(
                        Intent.ACTION_MAIN,
                        Intent.CATEGORY_APP_BROWSER
                    )
                    defaultBrowser.data = Uri.parse(webUrl)
                    startActivity(defaultBrowser)
                }
            }
        })
    }
}