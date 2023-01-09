package com.example.app.snackbar

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import com.example.rssapp.R
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(text:String) {
    Snackbar.make(
        this,
        text,
        BaseTransientBottomBar.LENGTH_SHORT
    ).show()
}