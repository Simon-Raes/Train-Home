package be.simonraes.trainhome.location

import android.location.Location
import be.simonraes.trainhome.entities.Station

class LocationUtils {

    companion object {
        val results = FloatArray(3)

        fun distanceBetween(station: Station, otherStation: Station) =
                distanceBetween(station.locationX.toDouble(),
                        station.locationY.toDouble(),
                        otherStation.locationX.toDouble(),
                        otherStation.locationY.toDouble())

        private fun distanceBetween(locationOneLat: Double,
                            locationOneLong: Double,
                            locationTwoLat: Double,
                            locationTwoLong: Double): Float {
            Location.distanceBetween(
                    locationOneLat, locationOneLong,
                    locationTwoLat, locationTwoLong,
                    results)

            return results[0]
        }

        fun currentLocation() {

        }

    }
}