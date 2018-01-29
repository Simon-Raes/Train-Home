package be.simonraes.trainhome.stations.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import be.simonraes.trainhome.R
import be.simonraes.trainhome.entities.Station
import be.simonraes.trainhome.stations.StationsClickListener

class StationsAdapter(private val context: Context, private val stationsClickListener: StationsClickListener) : RecyclerView.Adapter<StationViewHolder>() {

    private var stations: List<Station>? = null

    fun setData(stations: List<Station>) {
        this.stations = stations
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): StationViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.listitem_station, parent, false)
        return StationViewHolder(view, stationsClickListener)
    }

    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        holder.bindData(stations?.get(position)!!)
    }

    override fun getItemCount() = stations?.size ?: 0
}