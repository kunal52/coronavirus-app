package com.brtracker.coronavirustrackerapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.brtracker.coronavirustrackerapp.R
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity()  {

    val TAG = MainActivity::class.java.simpleName

    private lateinit var firebaseAnalytics : FirebaseAnalytics;
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progress_bar.hide()
        firebaseAnalytics=FirebaseAnalytics.getInstance(this)
        setUpBottomNavigation()



    }

    override fun onSupportNavigateUp() =
        findNavController(R.id.app_nav_host_fragment).navigateUp()

    private fun setUpBottomNavigation()
    {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.app_nav_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(bottom_navigation,
            navHostFragment.navController
        );
    }
}
