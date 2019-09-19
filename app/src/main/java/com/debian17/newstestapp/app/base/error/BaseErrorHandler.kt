package com.debian17.newstestapp.app.base.error

import android.content.Context
import com.debian17.newstestapp.R
import com.debian17.newstestapp.data.exception.NoNetworkException

abstract class BaseErrorHandler {

    open fun getErrorMessage(context: Context, throwable: Throwable): String {
        return when (throwable) {
            is NoNetworkException -> context.getString(R.string.no_network_connection)
            else -> context.getString(R.string.unknown_error_message)
        }
    }

}