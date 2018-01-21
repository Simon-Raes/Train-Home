package be.simonraes.trainhome.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import be.simonraes.trainhome.entities.Station.Companion.STATIONS_TABLE
import com.google.gson.annotations.SerializedName


data class Stations(
        @SerializedName("version") val version: String,
        @SerializedName("timestamp") val timestamp: String,
        @SerializedName("station") val stations: List<Station>
)

@Entity(tableName = STATIONS_TABLE)
data class Station(
        @PrimaryKey
        @ColumnInfo(name = STATIONS_COLUMN_ID)
        @SerializedName("id") val id: String,
        @ColumnInfo(name = STATIONS_COLUMN_LOCATION_X)
        @SerializedName("locationX") val locationX: String, // = longitude
        @ColumnInfo(name = STATIONS_COLUMN_LOCATION_Y)
        @SerializedName("locationY") val locationY: String, // = latitude
        @ColumnInfo(name = STATIONS_COLUMN_STANDARDNAME)
        @SerializedName("standardname") val standardname: String,
        @ColumnInfo(name = STATIONS_COLUMN_NAME)
        @SerializedName("name") val name: String
) {
    companion object {
        const val STATIONS_TABLE = "stations"
        const val STATIONS_COLUMN_ID = "id"
        const val STATIONS_COLUMN_LOCATION_X = "locationX"
        const val STATIONS_COLUMN_LOCATION_Y = "locationY"
        const val STATIONS_COLUMN_STANDARDNAME = "standardname"
        const val STATIONS_COLUMN_NAME = "name"
    }
}