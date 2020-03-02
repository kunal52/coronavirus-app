package com.brtracker.coronavirustrackerapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.brtracker.coronavirustrackerapp.api.InitialDataDownload
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity()  {

    val TAG =MainActivity::class.java.simpleName

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progress_bar.hide()

        bottom_navigation.setOnNavigationItemSelectedListener {

            when(it.itemId)
            {
                R.id.total ->{
                    Log.d(TAG,"Total")
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.data ->{
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.plots->{
                    return@setOnNavigationItemSelectedListener true
                }
                else ->
                {
                    return@setOnNavigationItemSelectedListener true
                }
            }

        }

        /* val rows: ArrayList<DataTableRow> = ArrayList()

         val header =DataTableHeader.Builder()
                 .item("Province/State", 2)
                 .item("Country/Region", 2)
                 .item("Confirmed", 2)
         .build();

         try {
             val openRawResource = resources.openRawResource(R.raw.test)
             val inputStreamReader = InputStreamReader(openRawResource)
             val identifier = resources.getIdentifier("test",
                     "raw", packageName);
             val reader = CSVReader(inputStreamReader)
             var nextLine: Array<String>
             while (reader.readNext().also { nextLine = it } != null) {
                 val row =  DataTableRow.Builder()
                         .value(nextLine[0])
                         .value(nextLine[1])
                         .value(nextLine[3])
                 .build()
                 rows.add(row);
             }






         } catch (e: Exception) {
             e.printStackTrace()
         }

         Log.d("TAG",rows.size.toString())

         //data_table.typeface= Typeface.DEFAULT
         data_table.rows = rows
         data_table.header=header
         data_table.inflate(this)
 */


    }
}
