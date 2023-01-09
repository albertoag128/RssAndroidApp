package com.example.rssapp.management.presentation

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.provider.ContactsContract.Data
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
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.serializer.GsonSerializer
import com.example.app.snackbar.showSnackbar
import com.example.rssapp.R
import com.example.rssapp.databinding.ActivityMainBinding
import com.example.rssapp.databinding.FragmentRssManagerBinding
import com.example.rssapp.management.data.xml.XmlLocalDataSource
import com.example.rssapp.management.presentation.adapter.RssManagerFeedAdapter
import androidx.navigation.NavDirections
import androidx.navigation.NavGraph
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.app.serializer.GsonSerializer
import com.example.rssapp.NavGraphDirections
import com.example.rssapp.R
import com.example.rssapp.databinding.ActivityMainBinding
import com.example.rssapp.databinding.FragmentRssManagerBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson

class RssManagerFragment : Fragment() {
    var binding: FragmentRssManagerBinding? = null
    var rssAdapter = RssManagerFeedAdapter()
    val viewModel by lazy {
        this.activity?.let {
            DataStoreFactory().injectViewModel(
                requireContext()
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
        viewModel?.obtainUserRssList()
    }

    fun navigateToBottomSheet() {
        findNavController().navigate(RssManagerFragmentDirections.actionToBottomSheet())
    }

    fun setupView() {
        binding?.apply {
            feedListRecyclerView.adapter = rssAdapter
            feedListRecyclerView.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            rssAdapter.setOnClick {
                viewModel?.deleteUserRss(it)
                (requireActivity()).findViewById<ViewGroup>(R.id.view_content).showSnackbar(
                    getString(R.string.snackbar_delete_text))
            }
        }
    }

    fun setupObservers() {
        val rssFeedSubscriber =
            Observer<RssDataStoreViewModel.UiState> { uiState ->
                uiState.userRssList?.let { rssAdapter?.setDataItems(it) }
            }
        viewModel?.uiState?.observe(viewLifecycleOwner, rssFeedSubscriber)
    }
}