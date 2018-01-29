package be.simonraes.trainhome.api

import be.simonraes.trainhome.entities.Stations
import be.simonraes.trainhome.home.connections.entities.Connections
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // todo include application name header!

    @GET("/stations/")
    fun getStations(@Query("format") format: String,
                    @Query("lang") language: String): Single<Stations>

    @GET("/connections/?format=json&lang=en")
    fun getConnections(@Query("from") from: String,
                       @Query("to") to: String): Single<Connections>
}