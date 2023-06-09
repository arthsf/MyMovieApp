package com.example.mymovieapp.presentation.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mymovieapp.data.cloud.base.ResourceProvider
import com.example.mymovieapp.domain.GetMovieActorsUseCase
import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.SaveMovieFromDetailsUseCase
import com.example.mymovieapp.domain.helper.DispatchersProvider
import com.example.mymovieapp.domain.models.movie.MovieDetailsDomain
import com.example.mymovieapp.domain.models.movie.MovieDomain
import com.example.mymovieapp.domain.models.movie.MoviesResponseDomain
import com.example.mymovieapp.domain.models.person.PersonDetailsDomain
import com.example.mymovieapp.domain.models.video.VideosResponseDomain
import com.example.mymovieapp.domain.repositories.network.MovieRepository
import com.example.mymovieapp.domain.repositories.network.VideoRepository
import com.example.mymovieapp.domain.repositories.storage.MovieStorageRepository
import com.example.mymovieapp.presentation.models.movie.MovieDetailsUi
import com.example.mymovieapp.presentation.models.movie.MovieUi
import com.example.mymovieapp.presentation.models.movie.MoviesResponseUi
import com.example.mymovieapp.presentation.models.person.PersonDetailsUi
import com.example.mymovieapp.presentation.models.video.VideosResponseUi
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject


private const val MOVIE_ID_KEY = "movie_id_key"
private const val ACTORS_IDS_KEY = "actors_ids_key"

class MovieDetailsViewModelFactory @AssistedInject constructor(
    @Assisted(MOVIE_ID_KEY) private val movieId: Int,
    @Assisted(ACTORS_IDS_KEY) private val actorsIds: List<Int>,
    private val movieRepository: MovieRepository,
    private val mapMovieDetails: Mapper<MovieDetailsDomain, MovieDetailsUi>,
    private val mapMovieResponse: Mapper<MoviesResponseDomain, MoviesResponseUi>,
    private val mapPersons: Mapper<List<PersonDetailsDomain>, List<PersonDetailsUi>>,
    private val videosResponseMapper: Mapper<VideosResponseDomain, VideosResponseUi>,
    private val videoRepository: VideoRepository,
    private val dispatchersProvider: DispatchersProvider,
    private val resourceProvider: ResourceProvider,
    private val getMovieActorsUseCase: GetMovieActorsUseCase,
    private val saveMovieFromDetailsUseCase: SaveMovieFromDetailsUseCase,
    private val movieStorageRepository: MovieStorageRepository,
    private val mapMovieToDomain: Mapper<MovieUi, MovieDomain>,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == MovieDetailsViewModel::class.java)
        return MovieDetailsViewModel(
            movieId = movieId,
            actorsIds = actorsIds,
            movieRepository = movieRepository,
            mapMovieDetails = mapMovieDetails,
            mapMovieResponse = mapMovieResponse,
            mapPersons = mapPersons,
            dispatchersProvider = dispatchersProvider,
            resourceProvider = resourceProvider,
            getMovieActorsUseCase = getMovieActorsUseCase,
            saveMovieFromDetailsUseCase = saveMovieFromDetailsUseCase,
            movieStorageRepository = movieStorageRepository,
            mapMovieToDomain = mapMovieToDomain,
            videoResponseMapper = videosResponseMapper,
            videoRepository = videoRepository
        ) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted(MOVIE_ID_KEY) movieId: Int,
            @Assisted(ACTORS_IDS_KEY) actorsIds: List<Int>,
        ): MovieDetailsViewModelFactory
    }
}