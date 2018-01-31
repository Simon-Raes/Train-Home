package be.simonraes.trainhome.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import be.simonraes.trainhome.R
import be.simonraes.trainhome.TrainHomeApplication
import be.simonraes.trainhome.constants.STATIONS
import be.simonraes.trainhome.constants.STATION_SELECTED
import be.simonraes.trainhome.home.connections.ConnectionsAdapter
import be.simonraes.trainhome.home.connections.ConnectionsClickListener
import be.simonraes.trainhome.home.connections.entities.DisplayConnection
import be.simonraes.trainhome.stations.StationsActivity
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject


class HomeActivity : AppCompatActivity(), HomePresenter.HomeView, ConnectionsClickListener {

    private lateinit var connectionsAdapter: ConnectionsAdapter

    @Inject
    lateinit var homePresenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        TrainHomeApplication.appComponent.inject(this)

        setupRecyclerView()
        setupPresenter()
        setupClickListeners()
    }

    private fun setupRecyclerView() {
        connectionsAdapter = ConnectionsAdapter(this, this)
        recyclerview_home_connections.adapter = connectionsAdapter
        recyclerview_home_connections.layoutManager = LinearLayoutManager(this)
    }

    private fun setupClickListeners() {
        layout_station_selection.setOnClickListener {
            startActivityForResult(StationsActivity.createIntent(this), STATIONS)
        }
        layout_station_selected.setOnClickListener {
            startActivityForResult(StationsActivity.createIntent(this), STATIONS)
        }
    }

    private fun setupPresenter() {
        homePresenter.attachView(this)
        homePresenter.start()
    }

    override fun onStop() {
        super.onStop()

        homePresenter.stop()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == STATIONS && resultCode == STATION_SELECTED) {
            homePresenter.onNewStationSelected()
        }
    }

    override fun onConnectionClicked() {
        // TODO some detai screen or something?
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

    override fun showConnectionsView(connections: List<DisplayConnection>) {
        connectionsAdapter.setData(connections)
    }

    override fun showNoConnectionsView() {
        // TODO some info message about how there are no connections today
        // or do we always show the next three, even for tomorrow

    }

}
