package com.poklad.jobinterviewtestproject.extensions

import android.content.Context
import android.view.View
import android.widget.Toast
import timber.log.Timber

fun Any.tag(): String {
    return this::class.simpleName!!
}

fun Any.log(msg: String) {
    Timber.d("TAG: ${tag()}", msg)
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}