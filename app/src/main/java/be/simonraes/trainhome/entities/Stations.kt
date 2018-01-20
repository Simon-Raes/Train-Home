package be.simonraes.trainhome.entities

import com.google.gson.annotations.SerializedName


data class Stations(
        @SerializedName("version") val version: String,
        @SerializedName("timestamp") val timestamp: String,
        @SerializedName("station") val stations: List<Station>
)

data class Station(
        @SerializedName("id") val id: String,
        @SerializedName("locationX") val locationX: String, // = longitude
        @SerializedName("locationY") val locationY: String, // = latitude
        @SerializedName("standardname") val standardname: String,
        @SerializedName("name") val name: String
)