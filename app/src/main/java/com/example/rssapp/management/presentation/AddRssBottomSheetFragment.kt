package com.example.rssapp.management.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.app.serializer.GsonSerializer
import com.example.rssapp.R
import com.example.rssapp.databinding.AddNewRssBottomSheetBinding
import com.example.rssapp.databinding.FragmentRssManagerBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class AddRssBottomSheetFragment : BottomSheetDialogFragment() {

    var rssManagerBinding:FragmentRssManagerBinding?=null
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
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSaveButton()
        setCancelButton()
    }

    fun setSaveButton() {
        binding?.apply {
            saveRssButton?.setOnClickListener {
                viewModel?.saveRss(
                    rssInputUrl.text.toString(),
                    rssInputName.text.toString()
                )
                findNavController().navigate(R.id.action_from_bottomSheet_to_rssManager)
                rssManagerBinding?.let { it1 -> showSnackbar(it1) }
            }
        }
    }

    fun setCancelButton() {
        binding?.cancelRssButton?.setOnClickListener {
            findNavController().navigate(R.id.action_from_bottomSheet_to_rssManager)
        }
    }

    fun showSnackbar(binding:FragmentRssManagerBinding) {
        binding.snackbar?.let {
            Snackbar.make(
                it,
                "Registro guardado",
                BaseTransientBottomBar.LENGTH_SHORT
            ).show()
        }
    }

}