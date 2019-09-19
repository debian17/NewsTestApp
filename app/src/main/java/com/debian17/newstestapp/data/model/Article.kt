package com.debian17.newstestapp.data.model

import com.google.gson.annotations.SerializedName

class Article(
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("urlToImage") val image: String?
)