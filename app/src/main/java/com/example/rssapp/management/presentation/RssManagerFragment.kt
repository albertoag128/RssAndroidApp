package com.example.rssapp.management.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.serializer.GsonSerializer
import com.example.app.snackbar.showSnackbar
import com.example.rssapp.R
import com.example.rssapp.databinding.FragmentRssManagerBinding
import com.example.rssapp.management.presentation.adapter.RssManagerFeedAdapter

class RssManagerFragment : Fragment() {
    private var binding: FragmentRssManagerBinding? = null
    private var rssAdapter = RssManagerFeedAdapter()
    private val viewModel by lazy {
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

    private fun openBottomSheet() {
        binding?.rssManagerToolbar?.apply {
            title = getString(R.string.rss_manager_fragment_title)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_add_new_rss -> navigateToBottomSheet()
                }
                true
            }
        }
    }

    private fun navigateToBottomSheet() {
        findNavController().navigate(RssManagerFragmentDirections.actionToBottomSheet())
    }

    private fun setupView() {
        binding?.apply {
            feedListRecyclerView.adapter = rssAdapter
            feedListRecyclerView.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            rssAdapter.setOnClick {
                viewModel?.deleteRss(it)
                (requireActivity()).findViewById<ViewGroup>(R.id.view_content).showSnackbar(
                    getString(R.string.snackbar_delete_text))
            }
        }
    }

    private fun setupObservers() {
        val rssFeedSubscriber =
            Observer<RssManagerViewModel.RssManagerFeedUiState> { uiState ->
                rssAdapter?.setDataItems(uiState.rssFeed)
            }
        viewModel?.rssManagerFeedPublisher?.observe(viewLifecycleOwner, rssFeedSubscriber)
    }

}