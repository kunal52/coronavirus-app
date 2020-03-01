import com.brtracker.coronavirustrackerapp.MyApplication
import okhttp3.*


val cacheSize = (5 * 1024 * 1024).toLong()

class AuthenticationIntercepter() : Interceptor {



    override fun intercept(chain: Interceptor.Chain): Response {


        val original: Request = chain.request()
        val originalHttpUrl: HttpUrl = original.url()

        val url = originalHttpUrl.newBuilder()
            .build()

        val requestBuilder: Request.Builder = original.newBuilder()
            .url(url)

        val request: Request = requestBuilder.build()


        return chain.proceed(request)


    }
}

var httpClient = OkHttpClient.Builder().cache(Cache(MyApplication.getAppContext()!!.cacheDir,cacheSize)).addInterceptor(AuthenticationIntercepter()).build()

