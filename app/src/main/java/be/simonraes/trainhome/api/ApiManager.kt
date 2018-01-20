package be.simonraes.trainhome.api

import be.simonraes.trainhome.entities.Stations
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by SimonRaes on 20/01/2018.
 */
class ApiManager @Inject constructor(private val apiService: ApiService) {

    fun stations(): Single<Stations> {
        return apiService.getStations("json", "en")
    }
}