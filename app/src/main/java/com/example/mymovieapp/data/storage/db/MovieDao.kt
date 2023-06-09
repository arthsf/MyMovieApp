package com.example.mymovieapp.data.storage.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mymovieapp.data.storage.model.MovieStorage
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM MOVIES_TABLE")
    fun getMoviesList(): Flow<List<MovieStorage>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movie: MovieStorage)

    @Query("DELETE FROM MOVIES_TABLE WHERE id=:movieId")
    suspend fun deleteMovie(movieId: Int)

}