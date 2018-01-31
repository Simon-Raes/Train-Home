package be.simonraes.trainhome.home.connections

import android.support.v7.widget.RecyclerView
import android.view.View
import be.simonraes.trainhome.home.connections.entities.Connection
import be.simonraes.trainhome.home.connections.entities.DisplayConnection
import kotlinx.android.synthetic.main.listitem_connection.view.*

/**
 * Single connection on the home(?) screen.
 */
class ConnectionViewHolder constructor(view: View, connectionsClickListener: ConnectionsClickListener) : RecyclerView.ViewHolder(view) {

    private lateinit var connection: Connection

    init {
        itemView.setOnClickListener { connectionsClickListener.onConnectionClicked() }
    }

    fun bindData(connection: DisplayConnection) {
        itemView.textview_connection_time.text = connection.departure
        itemView.textview_connection_duration.text = connection.duration
    }
}