package com.brtracker.coronavirustrackerapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.brtracker.coronavirustrackerapp.R
import com.brtracker.coronavirustrackerapp.api.InitialDataDownload
import com.opencsv.CSVReader
import ir.androidexception.datatable.model.DataTableHeader
import ir.androidexception.datatable.model.DataTableRow
import kotlinx.android.synthetic.main.fragment_detail_layout.*
import java.io.File
import java.io.FileReader

class DetailLayout : Fragment() {

    val TAG = DetailLayout::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress_bar.hide()

        val date = arguments?.getString("date")
        val category = arguments?.getString("category")
        Log.d(TAG, category!!)
        Log.d(TAG, date!!)


        val fileName = "$date.csv"


        if (checkFileExist(fileName)) {
            initUI(category, fileName)
        } else {
            val initialDataDownload =
                InitialDataDownload(context!!, object : InitialDataDownload.DownloadListener {
                    override fun onCompleted() {
                        progress_bar.hide()
                        initUI(category, fileName)
                        Toast.makeText(context, "Data Downloaded", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError() {
                        progress_bar.hide()
                        Toast.makeText(context, "Error Downloading Data", Toast.LENGTH_SHORT).show()
                    }

                    override fun onStarted() {
                        progress_bar.show()
                        Toast.makeText(context, "Download Data", Toast.LENGTH_LONG).show()
                    }

                })

            initialDataDownload.downloadSingleFile(fileName)

        }


    }

    private fun initUI(category: String, fileName: String) {
        when (category) {
            "Confirmed" -> {
                val header = DataTableHeader.Builder()
                    .item("Province/State", 1)
                    .item("Country/Region", 1)
                    .item("Confirmed", 1)
                    .build()

                val readingCSV = readingCSVForConfirmed(fileName)
                readingCSV.removeAt(0)


                data_table.rows = readingCSV
                data_table.header = header
                data_table.inflate(context!!)
            }
            "Deaths" -> {
                val header = DataTableHeader.Builder()
                    .item("Province/State", 1)
                    .item("Country/Region", 1)
                    .item("Deaths", 1)
                    .build()

                val readingCSV = readingCSVForDeaths(fileName)
                readingCSV.removeAt(0)


                data_table.rows = readingCSV
                data_table.header = header
                data_table.inflate(context!!)
            }
            "Recovered" -> {
                val header = DataTableHeader.Builder()
                    .item("Province/State", 1)
                    .item("Country/Region", 1)
                    .item("Recovered", 1)
                    .build()

                val readingCSV = readingCSVForRecovered(fileName)
                readingCSV.removeAt(0)


                data_table.rows = readingCSV
                data_table.header = header
                data_table.inflate(context!!)
            }
            else -> {

            }
        }
    }


    private fun readingCSVForConfirmed(fileName: String): ArrayList<DataTableRow> {

        val rows = ArrayList<DataTableRow>()
        try {
            val csvFileDownload =
                File(context!!.filesDir, fileName)
            val inputStreamReader = FileReader(csvFileDownload)
            val reader = CSVReader(inputStreamReader)
            var nextLine: Array<String>
            while (reader.readNext().also { nextLine = it } != null) {

                val row = DataTableRow.Builder()
                    .value(nextLine[0])
                    .value(nextLine[1])
                    .value(nextLine[3])
                    .build()
                rows.add(row);

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return rows
    }


    private fun readingCSVForDeaths(fileName: String): ArrayList<DataTableRow> {

        val rows = ArrayList<DataTableRow>()
        try {
            val csvFileDownload =
                File(context!!.filesDir, fileName)
            val inputStreamReader = FileReader(csvFileDownload)
            val reader = CSVReader(inputStreamReader)
            var nextLine: Array<String>
            while (reader.readNext().also { nextLine = it } != null) {

                val row = DataTableRow.Builder()
                    .value(nextLine[0])
                    .value(nextLine[1])
                    .value(nextLine[4])
                    .build()
                rows.add(row);

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return rows
    }


    private fun readingCSVForRecovered(fileName: String): ArrayList<DataTableRow> {

        val rows = ArrayList<DataTableRow>()
        try {
            val csvFileDownload =
                File(context!!.filesDir, fileName)
            val inputStreamReader = FileReader(csvFileDownload)
            val reader = CSVReader(inputStreamReader)
            var nextLine: Array<String>
            while (reader.readNext().also { nextLine = it } != null) {

                val row = DataTableRow.Builder()
                    .value(nextLine[0])
                    .value(nextLine[1])
                    .value(nextLine[5])
                    .build()
                rows.add(row);

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return rows
    }


    private fun checkFileExist(fileName: String): Boolean {
        val csvFileDownload =
            File(context!!.filesDir, fileName)
        if (csvFileDownload.exists())
            return true
        return false
    }
}
