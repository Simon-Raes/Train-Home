package be.simonraes.trainhome.home.connections.entities
import com.google.gson.annotations.SerializedName


/**
 * Created by SimonRaes on 29/01/2018.
 */

data class Connections(
		@SerializedName("version") val version: String,
		@SerializedName("timestamp") val timestamp: Int,
		@SerializedName("connection") val connection: List<Connection>
)

data class Connection(
		@SerializedName("id") val id: Int,
		@SerializedName("departure") val departure: Departure,
		@SerializedName("arrival") val arrival: Arrival,
		@SerializedName("duration") val duration: Int,
		@SerializedName("alerts") val alerts: Alerts,
		@SerializedName("vias") val vias: Vias?
)

data class Arrival(
		@SerializedName("delay") val delay: Int,
		@SerializedName("station") val station: String,
		@SerializedName("stationinfo") val stationinfo: Stationinfo,
		@SerializedName("time") val time: Int,
		@SerializedName("vehicle") val vehicle: String,
		@SerializedName("vehicleinfo") val vehicleinfo: Vehicleinfo,
		@SerializedName("platform") val platform: String,
		@SerializedName("platforminfo") val platforminfo: Platforminfo,
		@SerializedName("arrived") val arrived: Int,
		@SerializedName("canceled") val canceled: Int,
		@SerializedName("walking") val walking: Int,
		@SerializedName("direction") val direction: Direction
)

data class Platforminfo(
		@SerializedName("name") val name: String,
		@SerializedName("normal") val normal: String
)

data class Stationinfo(
		@SerializedName("id") val id: String,
		@SerializedName("@id") val atId: String,
		@SerializedName("locationX") val locationX: Double,
		@SerializedName("locationY") val locationY: Double,
		@SerializedName("standardname") val standardname: String,
		@SerializedName("name") val name: String
)

data class Vehicleinfo(
		@SerializedName("name") val name: String,
		@SerializedName("shortname") val shortname: String,
		@SerializedName("@id") val id: String
)

data class Direction(
		@SerializedName("name") val name: String
)

data class Alerts(
		@SerializedName("number") val number: Int,
		@SerializedName("alert") val alert: List<Alert>
)

data class Alert(
		@SerializedName("id") val id: Int,
		@SerializedName("header") val header: String,
		@SerializedName("lead") val lead: String,
		@SerializedName("link") val link: String,
		@SerializedName("startTime") val startTime: Int,
		@SerializedName("endTime") val endTime: Int
)

data class Vias(
		@SerializedName("number") val number: String,
		@SerializedName("via") val via: List<Via>
)

data class Via(
		@SerializedName("id") val id: String,
		@SerializedName("arrival") val arrival: Arrival,
		@SerializedName("departure") val departure: Departure,
		@SerializedName("timeBetween") val timeBetween: Int,
		@SerializedName("station") val station: String,
		@SerializedName("stationinfo") val stationinfo: Stationinfo,
		@SerializedName("vehicle") val vehicle: String,
		@SerializedName("direction") val direction: Direction
)

data class Departure(
		@SerializedName("time") val time: Int,
		@SerializedName("platform") val platform: Int,
		@SerializedName("platforminfo") val platforminfo: Platforminfo,
		@SerializedName("left") val left: Int,
		@SerializedName("delay") val delay: Int,
		@SerializedName("canceled") val canceled: Int,
		@SerializedName("departureConnection") val departureConnection: String,
		@SerializedName("vehicle") val vehicle: String,
		@SerializedName("walking") val walking: Int,
		@SerializedName("alerts") val alerts: Alerts,
		@SerializedName("direction") val direction: Direction
)
