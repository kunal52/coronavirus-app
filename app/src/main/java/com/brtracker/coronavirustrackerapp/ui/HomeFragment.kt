package com.brtracker.coronavirustrackerapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.brtracker.coronavirustrackerapp.R
import com.brtracker.coronavirustrackerapp.adapter.DateRecyclerviewAdapter
import com.brtracker.coronavirustrackerapp.api.NetworkApi
import com.brtracker.coronavirustrackerapp.util.getDateFromUtl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    private val TAG = HomeFragment::class.java.simpleName

    lateinit var dateRecyclerviewAdapter: DateRecyclerviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dateRecyclerviewAdapter = DateRecyclerviewAdapter(context!!)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        date_recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dateRecyclerviewAdapter

        }


        NetworkApi.create()
            .getFilesArray()
            .map { it.body() }
            .map {
                val list = ArrayList<String>()
                it.forEach { gitFileInfo ->
                    list.add(getDateFromUtl(gitFileInfo.downloadUrl))
                }
                return@map list
            }
            .flatMapIterable { it }
            .skip(1)
            .skipLast(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(TAG, it.toString())
                dateRecyclerviewAdapter.addDate(it)
            }, {
                it.printStackTrace()
            })


    }
}
