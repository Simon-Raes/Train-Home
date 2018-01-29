package be.simonraes.trainhome.home

import be.simonraes.trainhome.home.connections.entities.Connection
import be.simonraes.trainhome.home.connections.model.ConnectionsDataManager
import be.simonraes.trainhome.location.LocationManager
import be.simonraes.trainhome.persistence.PreferencesHelper
import be.simonraes.trainhome.persistence.db.StationDao
import be.simonraes.trainhome.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class HomePresenter @Inject constructor(private val preferencesHelper: PreferencesHelper,
                                        private val stationDao: StationDao,
                                        private val locationManager: LocationManager,
                                        private val connectionsDataManager: ConnectionsDataManager,
                                        private val schedulerProvider: SchedulerProvider) {
    interface HomeView {
        fun showStationSelectionView()

        fun showStationSelectedView()

        fun setSelectedStationInfo(name: String)

        fun showConnectionsView(connections: List<Connection>)

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

            // todo add an option to find the closes station to a fixed location later
            // gps location based only for now
            val disposable = stationDao.getAllAsSingle()
                    // todo move to datamanager
                    .flatMap { locationManager.findClosestStation(it) }
                    .flatMap {
                        connectionsDataManager.getConnections(it, preferencesHelper.getHomeStation())
                                .subscribeOn(schedulerProvider.io())
                    }
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .subscribe(
                            { connections -> println(connections) },
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