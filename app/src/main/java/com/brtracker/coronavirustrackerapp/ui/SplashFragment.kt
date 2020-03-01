package com.brtracker.coronavirustrackerapp.ui


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.brtracker.coronavirustrackerapp.R
import com.brtracker.coronavirustrackerapp.api.InitialDataDownload
import com.brtracker.coronavirustrackerapp.util.SharedPreferenceUtil
import kotlinx.android.synthetic.main.activity_main.*

class SplashFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (SharedPreferenceUtil.getIsInitialDataDownloaded(context!!))
        {
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        }
        else
        {
            val initialDataDownload = InitialDataDownload(context!!,object : InitialDataDownload.DownloadListener
            {
                override fun onCompleted() {
                    progress_bar.hide()
                    Toast.makeText(context,"Download Completed", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                    SharedPreferenceUtil.saveInitialDataDownloaded(context!!,true)
                }

                override fun onError() {
                    progress_bar.hide()
                    Toast.makeText(context,"Download Error", Toast.LENGTH_SHORT).show()
                }

                override fun onStarted() {
                    progress_bar.show()
                    Toast.makeText(context,"Initial Data Downloading", Toast.LENGTH_SHORT).show()
                }

            })

            initialDataDownload.downloadDataFiles()
        }
    }
}
