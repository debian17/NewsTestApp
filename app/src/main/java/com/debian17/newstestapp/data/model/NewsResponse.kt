package com.debian17.newstestapp.data.model

import com.google.gson.annotations.SerializedName

class NewsResponse(@SerializedName("articles") val news: List<Article>)