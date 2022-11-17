package com.example.rssapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.rssapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    var binding:ActivityMainBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupNavigation()
    }

    fun setupBinding(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding?.let {
            setContentView(it.root)
        }
    }

    fun setupNavigation(){
        findViewById<BottomNavigationView>(R.id.bottom_menu).setOnItemSelectedListener {
            when(it.itemId){
                R.id.to_rss_feed_item -> navigateToRssFeed()
                R.id.to_rss_manager_item -> navigateToRssManager()
                R.id.to_your_profile_item -> navigateToProfile()
            }
            true
        }

    }

    fun navigateToRssFeed(){
        findNavController(R.id.fragment_container_view).navigate(R.id.rss_feed)
    }

    fun navigateToRssManager(){
        findNavController(R.id.fragment_container_view).navigate(R.id.rss_manager)
    }

    fun navigateToProfile(){
        findNavController(R.id.fragment_container_view).navigate(R.id.profile)
    }
}