package be.simonraes.trainhome.stations

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import be.simonraes.trainhome.R
import be.simonraes.trainhome.TrainHomeApplication
import be.simonraes.trainhome.entities.Station
import javax.inject.Inject

/**
 * Created by SimonRaes on 21/01/2018.
 */
class StationsActivity: AppCompatActivity(), StationsPresenter.StationsView {

    companion object {
        fun createIntent(context: Context): Intent = Intent(context, StationsActivity::class.java)
    }

    @Inject
    lateinit var stationsPresenter: StationsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TrainHomeApplication.appComponent.inject(this)

        stationsPresenter.attachView(this)
        stationsPresenter.start()
    }

    override fun onStop() {
        super.onStop()

        stationsPresenter.stop()
    }

    override fun setData(stations: List<Station>) {
        println("got data from the presenter: $stations")
    }
}