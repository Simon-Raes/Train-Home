package be.simonraes.trainhome

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import be.simonraes.trainhome.api.ApiManager
import be.simonraes.trainhome.entities.Stations
import be.simonraes.trainhome.location.LocationManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var apiManager: ApiManager
    @Inject
    lateinit var locationManager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TrainHomeApplication.appComponent.inject(this)

        apiManager.stations()
                .map { stations: Stations -> stations.stations }
                .flatMap(locationManager::findClosestStation)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { data -> println("Closest station to you is: $data") },
                        { error -> println("something broke: $error.message") })
    }
}
