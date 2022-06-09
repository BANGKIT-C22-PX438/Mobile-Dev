package com.example.chicky.ui.dashboard

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    var title:String,
    var url:String
):Parcelable
