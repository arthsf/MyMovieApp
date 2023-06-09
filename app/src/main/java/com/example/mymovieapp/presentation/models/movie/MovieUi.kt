package com.example.mymovieapp.presentation.models.movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class MovieUi(
    val posterPath: String?,//
    val adult: Boolean,
    val overview: String,//
    val releaseDate: String?,
    val id: Int,//
    val originalTitle: String?,
    val originalLanguage: String,
    val title: String?,
    val backdropPath: String?,//
    val popularity: Double,//
    val voteCount: Int,
    val video: Boolean,
    val rating: Double,//
    val genre_ids: List<Int>,//
) : Parcelable