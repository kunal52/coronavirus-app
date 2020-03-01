package com.brtracker.coronavirustrackerapp.api

import android.annotation.SuppressLint
import android.content.Context
import android.os.Environment
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.HttpUrl
import okhttp3.ResponseBody
import java.io.*

class InitialDataDownload(val context: Context,private val listener: DownloadListener) {

    private val TAG = InitialDataDownload::class.java.simpleName

    interface DownloadListener {
        fun onCompleted()
        fun onError()
        fun onStarted()
    }

    @SuppressLint("CheckResult")
    fun downloadDataFiles() {

        listener.onStarted()

        NetworkApi.create().getFilesArray()
            .map { it.body() }
            .flatMapIterable { it }
            .skip(1)
            .skipLast(1)
            .flatMap {
                NetworkApi.create()
                    .downloadFileWithDynamicUrlSync(it.downloadUrl)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnComplete { listener.onCompleted() }
            .subscribe({
                Log.d(TAG, it.raw().request().url().toString())
                if (it.isSuccessful)
                    writeResponseBodyToDisk(
                        it.body()!!,
                        getFileNameFromUrl(it.raw().request().url())
                    )
            }, {
                it.printStackTrace()
            })
    }


    private fun getFileNameFromUrl(url: HttpUrl): String {
        val encodedPathSegments = url.encodedPathSegments()
        return encodedPathSegments.get(encodedPathSegments.size - 1)
    }

    private fun writeResponseBodyToDisk(body: ResponseBody, fileName: String): Boolean {
        return try {
            val csvFileDownload =
                File(context.filesDir, fileName)
            Log.d(TAG, csvFileDownload.path)
            if (!csvFileDownload.exists()) csvFileDownload.createNewFile()
            var inputStream: InputStream? = null
            var outputStream: OutputStream? = null
            try {
                val fileReader = ByteArray(4096)
                val fileSize = body.contentLength()
                var fileSizeDownloaded: Long = 0
                inputStream = body.byteStream()
                outputStream = FileOutputStream(csvFileDownload)
                while (true) {
                    val read = inputStream.read(fileReader)
                    if (read == -1) {
                        break
                    }
                    outputStream.write(fileReader, 0, read)
                    fileSizeDownloaded += read.toLong()
                    Log.d(
                        TAG,
                        "file download: $fileSizeDownloaded of $fileSize"
                    )
                }
                outputStream.flush()
                true
            } catch (e: IOException) {
                e.printStackTrace()
                false
            } finally {
                inputStream?.close()
                outputStream?.close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }


}