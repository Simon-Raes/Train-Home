package be.simonraes.trainhome.location

import android.location.Location
import be.simonraes.trainhome.entities.Station
import be.simonraes.trainhome.location.entities.LatLong

val results = FloatArray(3)

fun Station.distanceTo(otherStation: Station): Float {
    Location.distanceBetween(
            locationY.toDouble(), locationX.toDouble(),
            otherStation.locationY.toDouble(), otherStation.locationX.toDouble(),
            results)

    return results[0]
}

fun Station.distanceTo(location: LatLong): Float {
    Location.distanceBetween(
            locationY.toDouble(), locationX.toDouble(),
            location.latitude, location.longitude,
            results)

    return results[0]
}