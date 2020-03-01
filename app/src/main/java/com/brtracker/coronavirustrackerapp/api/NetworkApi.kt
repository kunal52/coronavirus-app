package com.brtracker.coronavirustrackerapp.api


import com.brtracker.coronavirustrackerapp.MyApplication
import com.brtracker.coronavirustrackerapp.model.GitFile
import io.reactivex.Observable
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url


interface NetworkApi {


    companion object {

        private const val cacheSize = (5 * 1024 * 1024).toLong()
        private var httpClient: OkHttpClient =
            OkHttpClient.Builder().cache(Cache(MyApplication.getAppContext()!!.cacheDir, cacheSize))
                .build()


        fun create(): NetworkApi {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://api.github.com/")
                .client(httpClient)
                .build()

            return retrofit.create(NetworkApi::class.java)
        }
    }

    @GET("repos/CSSEGISandData/COVID-19/contents/csse_covid_19_data/csse_covid_19_daily_reports")
    fun getFilesArray(): Observable<Response<List<GitFile>>>

    @GET
    fun downloadFileWithDynamicUrlSync(@Url fileUrl: String?): Observable<Response<ResponseBody>>

}