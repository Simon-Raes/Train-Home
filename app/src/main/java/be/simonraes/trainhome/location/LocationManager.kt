package be.simonraes.trainhome.location

import android.location.Location
import be.simonraes.trainhome.entities.Station
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Created by SimonRaes on 20/01/2018.
 */

class LocationManager @Inject constructor(val locationRetriever: LocationRetriever) {

    fun findClosestStation(stations: List<Station>): Single<Station> {

        return Single.create({ emitter: SingleEmitter<Station> ->

            lateinit var closestStation: Station
            var smallestDistance = Float.MAX_VALUE;
            val results = FloatArray(3)


            locationRetriever.currentLocation()
                    .map { location ->

                        stations.forEach({
                            Location.distanceBetween(
                                    it.locationY.toDouble(), it.locationX.toDouble(),
                                    // TODO use real location instead of hardcoded dampoort
//                                    location.latitude, location.longitude,
                                    51.056819, 3.740584,
                                    results)

                            if (results[0] < smallestDistance) {
                                closestStation = it
                                smallestDistance = results[0]
                            }
                        })

                        emitter.onSuccess(closestStation)
                    }


        })

    }

}