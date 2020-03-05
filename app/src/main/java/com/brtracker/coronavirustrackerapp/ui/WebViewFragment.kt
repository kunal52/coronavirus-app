package com.brtracker.coronavirustrackerapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.brtracker.coronavirustrackerapp.R
import kotlinx.android.synthetic.main.fragment_web_view.*

/**
 * A simple [Fragment] subclass.
 */
class WebViewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_web_view, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView.loadUrl("https://www.google.co.in/")

    }
}
