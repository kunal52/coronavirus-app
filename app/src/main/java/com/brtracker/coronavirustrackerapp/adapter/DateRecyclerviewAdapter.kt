package com.brtracker.coronavirustrackerapp.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.brtracker.coronavirustrackerapp.R
import com.brtracker.coronavirustrackerapp.dialog.SelectCategory
import kotlinx.android.synthetic.main.date_layout_item.view.*
import java.util.*
import kotlin.collections.ArrayList

class DateRecyclerviewAdapter(val context: Context) : RecyclerView.Adapter<DateRecyclerviewAdapter.ViewHolder>() {


     val TAG = DateRecyclerviewAdapter::class.java.simpleName

    val dates = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.date_layout_item, parent, false)
        return ViewHolder(view,context)
    }

    override fun getItemCount(): Int {
        return dates.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dates[position])
    }

    fun setItems(dates:List<String>)
    {
        this.dates.addAll(dates)
        notifyDataSetChanged()
    }

    fun addDate(date:String)
    {
        this.dates.add(0,date)
        notifyItemInserted(0)
    }

    fun sortData()
    {
        Collections.sort(this.dates, Collections.reverseOrder())
        notifyDataSetChanged()
    }

    class ViewHolder(itemView:View,val context: Context) : RecyclerView.ViewHolder(itemView) {

        private val TAG = ViewHolder::class.java.simpleName

        private val dateText: TextView =itemView.date_text
        fun bind(s: String)
        {
            dateText.text=s
            itemView.setOnClickListener {
                Log.d(TAG,itemView.date_text.text.toString())

                SelectCategory.newInstance(itemView.date_text.text.toString()).show((context as FragmentActivity).supportFragmentManager, "dialog")
            }
        }

    }
}