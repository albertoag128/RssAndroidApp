package com.example.rssapp.management.presentation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Layout.Directions
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.findFragment
import androidx.navigation.NavDirections
import androidx.navigation.NavGraph
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.app.serializer.GsonSerializer
import com.example.rssapp.R
import com.example.rssapp.databinding.ActivityMainBinding
import com.example.rssapp.databinding.FragmentRssManagerBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson

class RssManagerFragment : Fragment() {

    var binding:FragmentRssManagerBinding?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRssManagerBinding.inflate(inflater)
        setupView()
        return binding?.root
    }

    fun setupView(){
        binding?.rssManagerToolbar?.apply {
            title = getString(R.string.rss_manager_fragment_title)
            setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.action_add_new_rss -> navigateToBottomSheet()
                }
                true
            }
        }
    }

    fun navigateToBottomSheet(){
        findNavController().navigate()
    }
}