package be.simonraes.trainhome.home

import be.simonraes.trainhome.entities.Station
import be.simonraes.trainhome.persistence.PreferencesHelper
import javax.inject.Inject


class HomePresenter @Inject constructor(val preferencesHelper: PreferencesHelper) {
    interface HomeView {
        fun showStationSelectionView()

        fun showStationSelectedView()

        fun setSelectedStationInfo(name: String)

        fun showConnectionsView(todo: Any)
    }

    private lateinit var homeView: HomeView

    fun attachView(homeView: HomeView) {
        this.homeView = homeView
    }

    fun start() {
        if (preferencesHelper.homeStationSet()) {
            homeView.showStationSelectedView()

            refreshSelectedStationName()

//            todo load connections
        } else {
            homeView.showStationSelectionView()
        }
    }


    fun onNewStationSelected() {
        refreshSelectedStationName()
    }

    private fun refreshSelectedStationName() {
        val name = preferencesHelper.getHomeStation().name
        homeView.setSelectedStationInfo(name)
    }
}