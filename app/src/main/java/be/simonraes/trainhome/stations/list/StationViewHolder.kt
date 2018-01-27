package be.simonraes.trainhome.stations.list

import android.support.v7.widget.RecyclerView
import android.view.View
import be.simonraes.trainhome.entities.Station
import be.simonraes.trainhome.stations.StationsClickListener
import kotlinx.android.synthetic.main.listitem_station.view.*


/**
 * Single station item in the list of stations.
 */
class StationViewHolder constructor(view: View, stationsClickListener: StationsClickListener) : RecyclerView.ViewHolder(view) {

    private lateinit var station: Station

    init{
        itemView.setOnClickListener { stationsClickListener.onStationClicked(station) }
    }

    fun bindData(station: Station) {
        this.station = station

        itemView.textview_station_name.text = station.name
//        itemView.textview_station_distance.text = "1,6km"
    }
}