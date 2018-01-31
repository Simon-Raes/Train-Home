package be.simonraes.trainhome.home

import be.simonraes.trainhome.home.connections.entities.DisplayConnection
import be.simonraes.trainhome.home.connections.model.ConnectionsDataManager
import be.simonraes.trainhome.location.LocationManager
import be.simonraes.trainhome.persistence.PreferencesHelper
import be.simonraes.trainhome.persistence.db.StationDao
import be.simonraes.trainhome.rx.SchedulerProvider
import be.simonraes.trainhome.utils.DateFormatter
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class HomePresenter @Inject constructor(private val preferencesHelper: PreferencesHelper,
                                        private val stationDao: StationDao,
                                        private val locationManager: LocationManager,
                                        private val connectionsDataManager: ConnectionsDataManager,
                                        private val dateFormatter: DateFormatter,
                                        private val schedulerProvider: SchedulerProvider) {
    interface HomeView {
        fun showStationSelectionView()

        fun showStationSelectedView()

        fun setSelectedStationInfo(name: String)

        fun showConnectionsView(connections: List<DisplayConnection>)

        fun showNoConnectionsView()
    }

    private lateinit var homeView: HomeView

    private val compositeDisposable = CompositeDisposable()

    fun attachView(homeView: HomeView) {
        this.homeView = homeView
    }

    fun start() {
        if (preferencesHelper.homeStationSet()) {
            homeView.showStationSelectedView()

            refreshSelectedStationName()

            // todo add an option to use a fixed station instead of location/proximity based
            // gps location based only for now
            val disposable = stationDao.getAllAsSingle()
                    // todo move (partially) to datamanager?
                    .flatMap { locationManager.findClosestStation(it) }
                    .flatMap {
                        connectionsDataManager.getConnections(it, preferencesHelper.getHomeStation())
                                .subscribeOn(schedulerProvider.io())
                    }
                    .flatMapObservable { Observable.fromIterable(it) }
                    .map { DisplayConnection.fromConnection(it, dateFormatter) }
                    .toList()
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .subscribe(
                            { connections -> homeView.showConnectionsView(connections) },
                            { println(it.message) })

            compositeDisposable.add(disposable)

        } else {
            homeView.showStationSelectionView()
        }
    }

    fun stop() {
        compositeDisposable.dispose()
    }

    fun onNewStationSelected() {
        refreshSelectedStationName()
    }

    private fun refreshSelectedStationName() {
        val name = preferencesHelper.getHomeStation().name
        homeView.setSelectedStationInfo(name)
    }
}