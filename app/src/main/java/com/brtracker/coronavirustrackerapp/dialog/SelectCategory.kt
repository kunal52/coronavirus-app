package com.brtracker.coronavirustrackerapp.dialog

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.brtracker.coronavirustrackerapp.R
import kotlinx.android.synthetic.main.fragment_select_category_list_dialog.*
import kotlinx.android.synthetic.main.fragment_select_category_list_dialog_item.view.*

class SelectCategory : BottomSheetDialogFragment() {
    val category: Array<String> = arrayOf("Confirmed", "Deaths", "Recovered")
    lateinit var date: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_select_category_list_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        date = arguments?.getString("date")!!
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = ItemAdapter()
    }

    private inner class ViewHolder internal constructor(
        inflater: LayoutInflater,
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(
        inflater.inflate(
            R.layout.fragment_select_category_list_dialog_item,
            parent,
            false
        )
    ) {

        internal val text: TextView = itemView.text

        init {
            text.setOnClickListener {
                Log.d("Selected",date)
                val bundle = bundleOf("category" to category[adapterPosition], "date" to date)
                findNavController().navigate(R.id.action_homeFragment_to_detailLayout, bundle)
                dismiss()
            }
        }
    }

    private inner class ItemAdapter internal constructor() :
        RecyclerView.Adapter<ViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context), parent)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.text.text = category[position]
        }

        override fun getItemCount(): Int {
            return category.size
        }
    }

    companion object {



        fun newInstance(date: String): SelectCategory =
            SelectCategory().apply {
                arguments = Bundle().apply {
                    putString("date", date)
                }
            }

    }

}

