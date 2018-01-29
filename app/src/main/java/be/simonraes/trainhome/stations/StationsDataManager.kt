package be.simonraes.trainhome.stations

import be.simonraes.trainhome.api.ApiManager
import be.simonraes.trainhome.entities.DisplayStation
import be.simonraes.trainhome.entities.Station
import be.simonraes.trainhome.entities.Stations
import be.simonraes.trainhome.persistence.PreferencesHelper
import be.simonraes.trainhome.persistence.db.StationDao
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class StationsDataManager @Inject constructor(val apiManager: ApiManager,
                                              val stationsDao: StationDao,
                                              val preferencesHelper: PreferencesHelper) {

    fun getStations(): Flowable<List<Station>> = stationsDao.getAll()

    /*
    * @return A list of DisplayStations, sorted on distance from the homeStation.
    */
    fun getSortedDisplayStations(): Single<List<DisplayStation>> {
        return stationsDao.getAllAsSingle()
                .flatMapObservable { Observable.fromIterable(it) }
                .map { DisplayStation.stationToDisplayStation(it, preferencesHelper.getHomeStation()) }
                .toSortedList({ station, otherStation -> (station.distanceFromCurrentLocation - otherStation.distanceFromCurrentLocation).toInt() })
    }

    fun downloadAndSaveStations(): Completable {
        return apiManager.stations()
                .map(Stations::stations)
                .flatMapCompletable(this::insertStations)
    }

    private fun insertStations(stations: List<Station>): Completable =
            Completable.fromAction { stationsDao.insertAll(stations) }

}