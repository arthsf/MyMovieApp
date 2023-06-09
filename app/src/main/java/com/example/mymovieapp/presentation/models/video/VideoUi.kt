package com.example.mymovieapp.presentation.models.video

data class VideoUi(
    val id: String,
    val iso31661: String,
    val iso6391: String,
    val key: String,
    val name: String,
    val official: Boolean,
    val publishedAt: String,
    val site: String,
    val size: Int,
    val type: String,
    var isClicked: Boolean? = false,
)