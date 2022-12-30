package com.example.app.snackbar

import android.view.View
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(text:String) {
    Snackbar.make(
        this,
        text,
        BaseTransientBottomBar.LENGTH_SHORT
    ).show()
}