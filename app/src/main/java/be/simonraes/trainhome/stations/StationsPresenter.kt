package be.simonraes.trainhome.stations

import be.simonraes.trainhome.entities.Station
import be.simonraes.trainhome.persistence.PreferencesHelper
import be.simonraes.trainhome.rx.SchedulerProvider
import io.reactivex.BackpressureStrategy
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class StationsPresenter @Inject constructor(val stationsDataManager: StationsDataManager,
                                            val schedulerProvider: SchedulerProvider,
                                            val preferencesHelper: PreferencesHelper) {

    interface StationsView {
        fun setData(stations: List<Station>)

        fun setStationSelectedResult()

        fun closeScreen()
    }

    private lateinit var stationsView: StationsView

    private val searchSubject = BehaviorSubject.createDefault<String>("")

    private val compositeDisposable = CompositeDisposable()

    private var filter = ""

    fun attachView(stationsView: StationsView) {
        this.stationsView = stationsView
    }

    fun start() {
        downloadAndSaveStations()
        displayStations()
    }

    private fun downloadAndSaveStations() {
        // TODO do this max once a day or something (unless manually refreshed)
        // simple check so we don't keep downloading during development: download list until home has been set
        if (!preferencesHelper.homeStationSet()) {
            val disposable = stationsDataManager.downloadAndSaveStations()
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .subscribe(
                            {
                                println("saved all stations!!")
                            },
                            { error -> println("something broke: $error.message") })
            compositeDisposable.addAll(disposable)
        }
    }

    private fun displayStations() {
        val stationsDisposable = searchSubject.toFlowable(BackpressureStrategy.LATEST)
                .flatMap { query ->
                    // todo test if this works with data updates
                    stationsDataManager.getByName(query)
                            .subscribeOn(schedulerProvider.io())
                }
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

    private fun shouldShowStation(station: Station): Boolean {
        if (filter == "") {
            return true
        }

        return station.name.contains(filter)
    }

    fun stop() {
        compositeDisposable.dispose()
    }

    fun onSearchTextChanged(newText: String) {
        searchSubject.onNext(newText)
    }

    fun stationSelected(station: Station) {
        preferencesHelper.saveHomeStation(station)

        stationsView.setStationSelectedResult()
        stationsView.closeScreen()
    }
}