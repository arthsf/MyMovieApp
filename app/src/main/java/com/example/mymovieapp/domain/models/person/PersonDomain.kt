package com.example.mymovieapp.domain.models.person

import com.example.mymovieapp.domain.models.movie.MovieDomain


data class PersonDomain(
    val profile_path: String?,
    val adult: Boolean,
    val id: Int,
    val known_for: List<MovieDomain>,
    val name: String,
    val popularity: Double,
)
