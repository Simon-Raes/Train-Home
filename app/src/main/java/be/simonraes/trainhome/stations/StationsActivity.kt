package be.simonraes.trainhome.stations

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import be.simonraes.trainhome.R
import be.simonraes.trainhome.TrainHomeApplication
import be.simonraes.trainhome.constants.STATION_SELECTED
import be.simonraes.trainhome.entities.Station
import be.simonraes.trainhome.stations.list.StationsAdapter
import kotlinx.android.synthetic.main.activity_stations.*
import javax.inject.Inject

/**
 * Lets the user select his home station.
 */
class StationsActivity : AppCompatActivity(), StationsPresenter.StationsView, StationsClickListener {

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, StationsActivity::class.java)
    }

    private lateinit var stationsAdapter: StationsAdapter

    @Inject
    lateinit var stationsPresenter: StationsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stations)

        TrainHomeApplication.appComponent.inject(this)

        setupRecyclerView()
        setupPresenter()
    }

    fun setupRecyclerView() {
        stationsAdapter = StationsAdapter(this, this)
        recyclerview_stations.adapter = stationsAdapter
        recyclerview_stations.layoutManager = LinearLayoutManager(this)
    }

    fun setupPresenter() {
        stationsPresenter.attachView(this)
        stationsPresenter.start()
    }

    override fun onStop() {
        super.onStop()

        stationsPresenter.stop()
    }

    override fun onStationClicked(station: Station) {
        stationsPresenter.stationSelected(station)

        // TODO better feedback than this
        Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show()
    }

    override fun setData(stations: List<Station>) {
        stationsAdapter.setData(stations)
    }

    override fun setStationSelectedResult() {
        setResult(STATION_SELECTED)
    }

    override fun closeScreen() {
        finish()
    }


}