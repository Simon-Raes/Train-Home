package be.simonraes.trainhome.stations

import be.simonraes.trainhome.entities.Station
import be.simonraes.trainhome.entities.Stations
import be.simonraes.trainhome.rx.SchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class StationsPresenter @Inject constructor(val stationsDataManager: StationsDataManager,
                                            val schedulerProvider: SchedulerProvider) {

    interface StationsView {
        fun setData(stations: List<Station>)
    }

    private lateinit var stationsView: StationsView

    private val compositeDisposable = CompositeDisposable()

    fun attachView(stationsView: StationsView) {
        this.stationsView = stationsView
    }

    fun start() {
        // TODO do this max once a day or something (unless manually refreshed)
        val disposable = stationsDataManager.downloadAndSaveStations()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                        {
                            println("saved all stations!!")
                        },
                        { error -> println("something broke: $error.message") })
        compositeDisposable.addAll(disposable)

        val stationsDisposable = stationsDataManager.getStations()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                        {
                            println("received a new list of stations: $it")
                            stationsView.setData(it)
                        },
                        { error -> println("something broke: ${error.message}") })
        compositeDisposable.addAll(stationsDisposable)
    }

    fun stop() {
        compositeDisposable.dispose()
    }
}