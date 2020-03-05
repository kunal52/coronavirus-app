package com.brtracker.coronavirustrackerapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager

import com.brtracker.coronavirustrackerapp.R
import com.brtracker.coronavirustrackerapp.adapter.NewsAdapter
import com.brtracker.coronavirustrackerapp.model.ArticleModel
import com.brtracker.coronavirustrackerapp.viewmodel.ArticleViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.integration.recyclerview.RecyclerViewPreloader
import com.bumptech.glide.util.ViewPreloadSizeProvider
import kotlinx.android.synthetic.main.fragment_news.*

/**
 * A simple [Fragment] subclass.
 */
class NewsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress_bar.hide()
        val newsAdapter = NewsAdapter(context!!)
        news_recyclerview.layoutManager = GridLayoutManager(context, 1)
        news_recyclerview.itemAnimator = DefaultItemAnimator()
        news_recyclerview.adapter = newsAdapter

        /*val preloadSizeProvider = ViewPreloadSizeProvider<ArticleModel>()
        val preloaded: RecyclerViewPreloader<ArticleModel> =
            RecyclerViewPreloader(
                Glide.with(this),
                glidePreloadProvider,
                preloadSizeProvider,
                10
            )
        news_recyclerview.addOnScrollListener(preloaded)*/

        val newsViewModel =
            ViewModelProvider(this).get(ArticleViewModel::class.java)

        newsViewModel.pixaImageEntityPagedList.observe(viewLifecycleOwner, Observer {
            newsAdapter.submitList(it)
        })


    }
}
