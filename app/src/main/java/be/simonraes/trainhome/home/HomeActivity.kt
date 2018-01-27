package be.simonraes.trainhome.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import be.simonraes.trainhome.R
import be.simonraes.trainhome.TrainHomeApplication
import be.simonraes.trainhome.constants.STATIONS
import be.simonraes.trainhome.constants.STATION_SELECTED
import be.simonraes.trainhome.stations.StationsActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class HomeActivity : AppCompatActivity(), HomePresenter.HomeView {

    @Inject
    lateinit var homePresenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TrainHomeApplication.appComponent.inject(this)

        homePresenter.attachView(this)
        homePresenter.start()

        layout_station_selection.setOnClickListener {
            startActivityForResult(StationsActivity.createIntent(this), STATIONS)
        }
        layout_station_selected.setOnClickListener {
            startActivityForResult(StationsActivity.createIntent(this), STATIONS)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == STATIONS && resultCode == STATION_SELECTED) {
            homePresenter.onNewStationSelected()
        }
    }

    override fun showStationSelectionView() {
        layout_station_selected.visibility = View.GONE
        layout_station_selection.visibility = View.VISIBLE
    }

    override fun showStationSelectedView() {
        layout_station_selected.visibility = View.VISIBLE
        layout_station_selection.visibility = View.GONE
    }

    override fun setSelectedStationInfo(name: String) {
        textview_home_selected_station.text = name
    }

    override fun showConnectionsView(todo: Any) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
