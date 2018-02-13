package be.simonraes.trainhome.home.connections.entities

import be.simonraes.trainhome.utils.DateFormatter

/**
 * todo properly create entities per layer
 */
data class DisplayConnection(val departure: String,
                             val arrival: String,
                             val duration: String,
                             val delay: String,
                             val vias: String) {
    companion object {
        fun fromConnection(connection: Connection, dateFormatter: DateFormatter): DisplayConnection {
            val formattedDeparture = dateFormatter.formatTimestamp(connection.departure.time.toLong())
            val formattedArrival = dateFormatter.formatTimestamp(connection.departure.time.toLong())
            val formattedDuration = dateFormatter.secondsToFormattedDuration(connection.duration.toLong())
            val formattedDelay = dateFormatter.secondsToFormattedDuration(connection.arrival.delay.toLong())
            val vias = connection.vias?.number ?: "0"

            return DisplayConnection(formattedDeparture, formattedArrival, formattedDuration, formattedDelay, vias)
        }
    }
}