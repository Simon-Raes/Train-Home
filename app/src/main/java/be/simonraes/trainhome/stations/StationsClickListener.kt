package be.simonraes.trainhome.stations

import be.simonraes.trainhome.entities.Station

/**
 * Created by SimonRaes on 27/01/2018.
 */
interface StationsClickListener {
    fun onStationClicked(station: Station)
}