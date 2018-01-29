package be.simonraes.trainhome.home.connections.model

import be.simonraes.trainhome.api.ApiManager
import be.simonraes.trainhome.entities.Station
import javax.inject.Inject

/**
 * Created by SimonRaes on 29/01/2018.
 */
class ConnectionsDataManager @Inject constructor(val apiManager: ApiManager) {

    /*
     * @return List of connections from station to otherStation.
     */
    fun getConnections(station: Station, otherStation: Station) =
            apiManager.connections(station, otherStation)
                    .map { it.connection }

}