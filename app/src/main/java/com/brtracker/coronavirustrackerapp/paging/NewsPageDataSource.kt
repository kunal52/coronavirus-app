package com.brtracker.pixawallpaper.data.paging

import android.annotation.SuppressLint
import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.brtracker.coronavirustrackerapp.api.NetworkApi
import com.brtracker.coronavirustrackerapp.model.ArticleModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class NewsPageDataSource : PageKeyedDataSource<Int, ArticleModel>() {


    @SuppressLint("CheckResult")
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ArticleModel>
    ) {

        NetworkApi.createApi().getNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.isSuccessful) {
                    callback.onResult(it.body()!!, null, 2)
                }
            }, {

            })

    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ArticleModel>) {
        NetworkApi.createApi().getNews(page = params.key)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("Retrofit", it.toString())
                if (it.isSuccessful) {
                    callback.onResult(it.body()!!, params.key + 1)
                }
            }, {

            })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ArticleModel>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}