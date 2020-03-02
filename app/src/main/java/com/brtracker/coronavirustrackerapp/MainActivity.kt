package com.brtracker.coronavirustrackerapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
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
