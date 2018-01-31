package be.simonraes.trainhome.home.connections

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import be.simonraes.trainhome.R
import be.simonraes.trainhome.home.connections.entities.Connection
import be.simonraes.trainhome.home.connections.entities.DisplayConnection

class ConnectionsAdapter(private val context: Context, private val connectionsClickListener: ConnectionsClickListener) : RecyclerView.Adapter<ConnectionViewHolder>() {

    private var connections: List<DisplayConnection>? = null

    fun setData(connections: List<DisplayConnection>) {
        this.connections = connections
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ConnectionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.listitem_connection, parent, false)
        return ConnectionViewHolder(view, connectionsClickListener)
    }

    override fun onBindViewHolder(holder: ConnectionViewHolder, position: Int) {
        holder.bindData(connections?.get(position)!!)
    }

    override fun getItemCount() = connections?.size ?: 0
}