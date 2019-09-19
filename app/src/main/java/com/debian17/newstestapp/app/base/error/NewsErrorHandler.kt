package com.debian17.newstestapp.app.base.error

import android.content.Context
import com.debian17.newstestapp.R
import com.debian17.newstestapp.data.model.NewsErrorResponse
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import retrofit2.HttpException
import java.io.*
import java.lang.RuntimeException

class NewsErrorHandler : BaseErrorHandler() {

    override fun getErrorMessage(context: Context, throwable: Throwable): String {
        return when (throwable) {
            is HttpException -> {
                val newsErrorResponse: NewsErrorResponse
                val gson = Gson()
                try {
                    val errorBody = throwable.response()?.errorBody()?.string()
                    if (errorBody == null) {
                        return context.getString(R.string.server_issues)
                    }
                    newsErrorResponse = gson.fromJson<NewsErrorResponse>(
                        errorBody,
                        NewsErrorResponse::class.java
                    )
                } catch (e: RuntimeException) {
                    return context.getString(R.string.server_issues)
                }
                val errorMessage = newsErrorResponse.message
                if (errorMessage == null) {
                    return context.getString(R.string.server_issues)
                } else {
                    return errorMessage
                }
            }
            else -> super.getErrorMessage(context, throwable)
        }
    }

}