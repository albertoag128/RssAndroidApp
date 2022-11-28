package com.example.rssapp.management.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.app.serializer.GsonSerializer
import com.example.rssapp.R
import com.example.rssapp.databinding.AddNewRssBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class RssBottomSheetFragment : BottomSheetDialogFragment() {

    var binding: AddNewRssBottomSheetBinding? = null
    val viewModel by lazy {
        this.activity?.let {
            RssManagerFactory().saveUserRss(
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
        binding = AddNewRssBottomSheetBinding.inflate(inflater)
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
                showSnackbar()
            }
            binding?.cancelRssButton?.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    fun showSnackbar() {
        Snackbar.make(
            (requireActivity()).findViewById<ViewGroup>(R.id.view_content),
            R.string.snackbar_text,
            BaseTransientBottomBar.LENGTH_SHORT
        ).show()
    }

}