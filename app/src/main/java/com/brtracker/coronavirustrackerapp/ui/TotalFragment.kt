package com.brtracker.coronavirustrackerapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.brtracker.coronavirustrackerapp.R
import com.brtracker.coronavirustrackerapp.api.InitialDataDownload
import com.brtracker.coronavirustrackerapp.api.NetworkApi
import com.brtracker.coronavirustrackerapp.model.CsvDataModel
import com.brtracker.coronavirustrackerapp.util.getFileNameFromUrl
import com.opencsv.CSVReader
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_total.*
import java.io.File
import java.io.FileReader

class TotalFragment : Fragment() {


    private val TAG=TotalFragment::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_total, container, false)
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NetworkApi.create()
            .getFilesArray()
            .map { it.body() }
            .map {
                val list = ArrayList<String>()
                it.forEach { gitFileInfo ->
                    list.add(gitFileInfo.downloadUrl)
                }
                return@map list
            }
            .flatMapIterable { it }
            .skipLast(1)
            .takeLast(1)
            .map {
                    return@map readingCSV(getFileNameFromUrl(it))
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                initUi(calculateTotal(it))


            }, {
                it.printStackTrace()
            })
    }



    fun initUi(data:Array<String>)
    {
        confirmed_text.text=data[0]
        deaths_text.text=data[1]
        recovered_text.text=data[2]
        last_updated_text.text="Last Updated "+data[3]
    }

    private fun calculateTotal(list: List<CsvDataModel>): Array<String> {

        var confirmed=0
        var deaths=0
        var recovered=0
        list.forEach {
            confirmed +=Integer.parseInt(it.confirmed)
            deaths +=Integer.parseInt(it.deaths)
            recovered +=Integer.parseInt(it.recovered)
        }

        return arrayOf(confirmed.toString(),deaths.toString(),recovered.toString(),list[1].lastUpdate.substring(0,10))

    }

    private fun checkFileExist(fileName: String): Boolean {
        val csvFileDownload =
            File(context!!.filesDir, fileName)
        if (csvFileDownload.exists())
            return true
        return false
    }

    private fun readingCSV(fileName: String): List<CsvDataModel> {

        val rows = ArrayList<CsvDataModel>()
        try {
            val csvFileDownload =
                File(context!!.filesDir, fileName)
            val inputStreamReader = FileReader(csvFileDownload)
            val reader = CSVReader(inputStreamReader)
            var nextLine: Array<String>
            while (reader.readNext().also { nextLine = it } != null) {

                val row = CsvDataModel()
                row.state=nextLine[0]
                row.country=nextLine[1]
                row.lastUpdate=nextLine[2]
                row.confirmed=nextLine[3]
                row.deaths=nextLine[4]
                row.recovered=nextLine[5]
                rows.add(row);

            }

            //Log.d(TAG,removeAt.toString())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val removeAt = rows.removeAt(0)
        return rows
    }

}
