package be.simonraes.trainhome.stations

import be.simonraes.trainhome.entities.Station
import be.simonraes.trainhome.location.LocationRetriever
import be.simonraes.trainhome.location.distanceTo
import be.simonraes.trainhome.location.entities.LatLong
import be.simonraes.trainhome.persistence.PreferencesHelper
import be.simonraes.trainhome.rx.SchedulerProvider
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Flowables
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class StationsPresenter @Inject constructor(private val stationsDataManager: StationsDataManager,
                                            private val locationRetriever: LocationRetriever,
                                            private val schedulerProvider: SchedulerProvider,
                                            private val preferencesHelper: PreferencesHelper) {

    interface StationsView {
        fun setData(stations: List<Station>)

        fun setStationSelectedResult()

        fun showNearestStationDialog(station: Station)

        fun showGenericErrorMessage()

        fun closeScreen()
    }

    private lateinit var stationsView: StationsView

    private val searchSubject = BehaviorSubject.createDefault<String>("")

    private val compositeDisposable = CompositeDisposable()

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
                            { error ->
                                stationsView.showGenericErrorMessage()
                                println("something broke: $error.message")
                            })
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
                        { error ->
                            stationsView.showGenericErrorMessage()
                            println("something broke: ${error.message}")
                        })
        compositeDisposable.addAll(stationsDisposable)
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

    fun onLocationClicked() {
        val currentLocationSingle = locationRetriever.currentLocation()
        val stationsFlowable = stationsDataManager.getStations()

        Flowables.combineLatest(currentLocationSingle.toFlowable(), stationsFlowable)
        { currentLocation: LatLong, stations: List<Station> ->
            stations.minBy { it.distanceTo(currentLocation) }
        }
                .map { station -> station }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { stationsView.showNearestStationDialog(it) },
                        {
                            println("something went wrong ${it.message}")
                            stationsView.showGenericErrorMessage()
                        })


        //

    }
}