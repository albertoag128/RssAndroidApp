package com.example.rssapp.management.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.app.serializer.GsonSerializer
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
                dismiss()
                showSnackbar()
            }
            binding?.cancelRssButton?.setOnClickListener {
                findNavController().navigate(R.id.action_from_bottomSheet_to_rssManager)
            }
        }
    }

    fun showSnackbar() {
        Snackbar.make(
            (requireActivity()).findViewById<ViewGroup>(R.id.view_content),
            "Registro guardado",
            BaseTransientBottomBar.LENGTH_SHORT
        ).show()
    }

}