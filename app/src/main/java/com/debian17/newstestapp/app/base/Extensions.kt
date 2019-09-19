package com.debian17.newstestapp.app.base

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.animation.AlphaAnimation
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject

const val ANIMATION_DURATION = 350L

fun View.show() {
    if (visibility != View.VISIBLE) {
        val alphaAnimation = AlphaAnimation(0f, 1f).apply {
            duration = ANIMATION_DURATION
        }
        startAnimation(alphaAnimation)
        visibility = View.VISIBLE
    }
}

fun View.hide() {
    if (visibility != View.GONE) {
        val alphaAnimation = AlphaAnimation(1f, 0f).apply {
            duration = ANIMATION_DURATION
        }
        startAnimation(alphaAnimation)
        visibility = View.GONE
    }
}

fun <T> PublishSubject<T>.observeOnUI() = observeOn(AndroidSchedulers.mainThread())

fun Context.openBrowser(url: String) {
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    val resolveInfo = packageManager?.resolveActivity(browserIntent, 0)
    if (resolveInfo != null) {
        startActivity(browserIntent)
    }
}
