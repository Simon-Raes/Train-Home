package be.simonraes.trainhome.location

import android.location.Location

class LocationUtils {

    companion object {
        val results = FloatArray(3)

        fun distanceBetween(locationOneLat: Double,
                            locationOneLong: Double,
                            locationTwoLat: Double,
                            locationTwoLong: Double): Float {
            Location.distanceBetween(
                    locationOneLat, locationOneLong,
                    locationTwoLat, locationTwoLong,
                    results)

            return results[0]
        }
    }
}