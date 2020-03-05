package com.brtracker.coronavirustrackerapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.brtracker.coronavirustrackerapp.model.ArticleModel
import com.brtracker.pixawallpaper.data.paging.NewsDataFactory
import com.brtracker.pixawallpaper.data.paging.NewsPageDataSource

class ArticleViewModel : ViewModel() {
    var pixaImageEntityPagedList: LiveData<PagedList<ArticleModel>>
    var liveDataSource: LiveData<NewsPageDataSource>

    init {
        val itemDataSourceFactory = NewsDataFactory()
        liveDataSource = itemDataSourceFactory.mutableLiveData

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .build()

        pixaImageEntityPagedList = LivePagedListBuilder(itemDataSourceFactory, config)
            .build()

    }
}