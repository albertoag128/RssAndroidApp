package com.example.rssapp.management.presentation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.findFragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.serializer.GsonSerializer
import com.example.rssapp.R
import com.example.rssapp.databinding.ActivityMainBinding
import com.example.rssapp.databinding.FragmentRssManagerBinding
import com.example.rssapp.management.data.xml.XmlLocalDataSource
import com.example.rssapp.management.presentation.adapter.RssManagerFeedAdapter
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson

class RssManagerFragment : Fragment() {
    var skeleton: Skeleton?=null
    var binding:FragmentRssManagerBinding?=null
    var rssAdapter = RssManagerFeedAdapter()
    val viewModel by lazy{
        this.activity?.let {
            RssManagerFactory().getRss(
                GsonSerializer(), it.getPreferences(Context.MODE_PRIVATE)
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRssManagerBinding.inflate(inflater)
        openBottomSheet()
        setupView()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        viewModel?.getRss()
    }

    fun openBottomSheet(){
        binding?.rssManagerToolbar?.apply {
            title = getString(R.string.rss_manager_fragment_title)
            setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.action_add_new_rss -> showBottomSheet()
                }
                true
            }
        }
    }

    fun showBottomSheet(){
        findNavController().navigate(R.id.action_from_rssManager_to_bottomSheet)
    }

    fun setupView(){
        binding?.apply {
            feedListRecyclerView.adapter = rssAdapter
            feedListRecyclerView.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            skeleton = feedListRecyclerView.applySkeleton(R.layout.fragment_rss_feed)
        }
    }

    fun setupObservers(){
        val rssFeedSubscriber =
            Observer<RssManagerViewModel.RssManagerFeedUiState> { uiState ->
                if (uiState.isLoading) {
                    skeleton?.showSkeleton()
                } else {
                    skeleton?.showOriginal()
                    rssAdapter?.setDataItems(uiState.rssFeed)
                }
            }
        viewModel?.rssManagerFeedPublisher?.observe(viewLifecycleOwner, rssFeedSubscriber)
    }


}