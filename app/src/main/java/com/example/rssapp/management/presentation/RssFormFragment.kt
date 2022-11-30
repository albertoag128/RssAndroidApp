package com.example.rssapp.management.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.app.serializer.GsonSerializer
import com.example.app.snackbar.showSnackbar
import com.example.rssapp.NavGraphDirections
import com.example.rssapp.R
import com.example.rssapp.databinding.RssUserFormBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class RssFormFragment : BottomSheetDialogFragment() {

    var binding: RssUserFormBinding? = null
    val viewModel by lazy {
        this.activity?.let {
            RssFormFactory().saveUserRss(
                it.getPreferences(Context.MODE_PRIVATE),
                GsonSerializer()
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RssUserFormBinding.inflate(inflater)
        setupView()
        return binding?.root
    }

    fun setupView(){
        binding?.apply {
            saveRssButton?.setOnClickListener {
                viewModel?.saveRss(
                    rssInputUrl.text.toString(),
                    rssInputName.text.toString()
                )
                findNavController().navigateUp()
                (requireActivity()).findViewById<ViewGroup>(R.id.view_content).showSnackbar(getString(R.string.snackbar_save_text))
            }
            binding?.cancelRssButton?.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }
}