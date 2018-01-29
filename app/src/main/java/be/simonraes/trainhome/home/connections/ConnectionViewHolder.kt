package be.simonraes.trainhome.home.connections

import android.support.v7.widget.RecyclerView
import android.view.View
import be.simonraes.trainhome.home.connections.entities.Connection
import be.simonraes.trainhome.utils.DateUtils
import kotlinx.android.synthetic.main.listitem_connection.view.*

/**
 * Single connection on the home(?) screen.
 */
class ConnectionViewHolder constructor(view: View, connectionsClickListener: ConnectionsClickListener) : RecyclerView.ViewHolder(view) {

    private lateinit var connection: Connection

    init {
        itemView.setOnClickListener { connectionsClickListener.onConnectionClicked() }
    }

    fun bindData(connection: Connection) {
        // todo format time
        itemView.textview_connection_time.text = DateUtils.formatStuff( connection.departure.time.toLong())
        itemView.textview_connection_duration.text = DateUtils.secondsToFormattedDuration( connection.duration.toLong())
    }
}