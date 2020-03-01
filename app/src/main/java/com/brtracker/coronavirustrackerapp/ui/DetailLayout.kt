package com.brtracker.coronavirustrackerapp.ui

import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.brtracker.coronavirustrackerapp.R
import com.opencsv.CSVReader
import ir.androidexception.datatable.model.DataTableHeader
import ir.androidexception.datatable.model.DataTableRow
import kotlinx.android.synthetic.main.fragment_detail_layout.*
import java.io.File
import java.io.FileReader
import java.io.InputStreamReader
import java.lang.Exception

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


        val date = arguments?.getString("date")
        val category = arguments?.getString("category")
        Log.d(TAG, category!!)
        Log.d(TAG, date!!)


        val header = DataTableHeader.Builder()
            .item("Province/State", 1)
            .item("Country/Region", 1)
            .item("Confirmed", 1)
            .build();


        val fileName = "$date.csv"


        val readingCSV = readingCSV(fileName)
        readingCSV.removeAt(0)


        data_table.rows = readingCSV
        data_table.header=header
        data_table.inflate(context!!)

    }


    fun readingCSV(fileName: String) : ArrayList<DataTableRow> {

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

                Log.d(TAG,nextLine[1])
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return rows
    }
}
