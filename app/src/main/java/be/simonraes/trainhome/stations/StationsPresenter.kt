package be.simonraes.trainhome.stations

import be.simonraes.trainhome.entities.Station
import be.simonraes.trainhome.entities.Stations
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by SimonRaes on 21/01/2018.
 */
class StationsPresenter @Inject constructor(val stationsDataManager: StationsDataManager) {

    interface StationsView {
        fun setData(stations: List<Station>)
    }

    private lateinit var stationsView: StationsView

    fun attachView(stationsView: StationsView) {
        this.stationsView = stationsView
    }

    fun start() {
        // TODO do this max once a day or something (unless manually refreshed)
        stationsDataManager.downloadAndSaveStations()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            println("saved all stations!!")
                        },
                        { error -> println("something broke: $error.message") })

        // todo unsubscribe again later
        stationsDataManager.getStations()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            println("received a new list of stations: $it")
                            stationsView.setData(it)
                        },
                        { error -> println("something broke: $error.message") })

    }
}