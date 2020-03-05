package com.brtracker.pixawallpaper.data.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.brtracker.coronavirustrackerapp.model.ArticleModel

class NewsDataFactory :
    DataSource.Factory<Int, ArticleModel>() {
    val mutableLiveData = MutableLiveData<NewsPageDataSource>()

    override fun create(): DataSource<Int, ArticleModel> {
        val pixaWallpaperPageDataSource = NewsPageDataSource()
        mutableLiveData.postValue(pixaWallpaperPageDataSource)
        return pixaWallpaperPageDataSource
    }

    fun search()
    {

    }

}