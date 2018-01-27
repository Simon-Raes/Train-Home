package be.simonraes.trainhome.stations

import be.simonraes.trainhome.api.ApiManager
import be.simonraes.trainhome.persistence.db.StationDao
import be.simonraes.trainhome.entities.Station
import be.simonraes.trainhome.entities.Stations
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class StationsDataManager @Inject constructor(val apiManager: ApiManager,
                                              val stationsDao: StationDao) {

    fun getStations(): Flowable<List<Station>> = stationsDao.getAll()

    fun downloadAndSaveStations(): Completable {
        return apiManager.stations()
                .map(Stations::stations)
                .flatMapCompletable(this::insertStations)
    }

    private fun insertStations(stations: List<Station>): Completable =
            Completable.fromAction { stationsDao.insertAll(stations) }

}