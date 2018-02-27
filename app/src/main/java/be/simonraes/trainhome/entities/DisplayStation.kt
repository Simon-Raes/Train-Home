package be.simonraes.trainhome.entities

import be.simonraes.trainhome.location.distanceTo

/**
 *
 * todo better class names
 * maybe create different entities per "layer"?
 * we don't need the db station entity in our view
 */
data class DisplayStation(val name: String,
                          val distanceFromCurrentLocation: Float) {
    companion object {
        fun stationToDisplayStation(station: Station, homeStation: Station): DisplayStation {
            return DisplayStation(station.name,
                    station.distanceTo(homeStation))
        }
    }
}

