package be.simonraes.trainhome.api

import be.simonraes.trainhome.entities.Stations
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // todo include application name header

    @GET("/stations/")
    fun getStations(@Query("format") format: String,
                    @Query("lang") language: String): Single<Stations>

    @GET("/connections/?from=BE.NMBS.008893120&to=BE.NMBS.008892106&format=json&lang=en")
    fun getConnections(@Query("from") from: String,
                       @Query("to") to: String,
                       @Query("format") format: String,
                       @Query("lang") language: String): Single<Object>
}