package be.simonraes.trainhome.api

import be.simonraes.trainhome.entities.Station
import be.simonraes.trainhome.entities.Stations
import be.simonraes.trainhome.home.connections.entities.Connections
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by SimonRaes on 20/01/2018.
 */
class ApiManager @Inject constructor(private val apiService: ApiService) {

    fun stations(): Single<Stations> {
        return apiService.getStations("json", "en")
    }

    fun connections(station: Station, otherStation: Station): Single<Connections> {
        return apiService.getConnections(station.id, otherStation.id)
    }
}