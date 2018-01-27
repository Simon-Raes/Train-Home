package be.simonraes.trainhome.persistence

import android.content.Context
import be.simonraes.trainhome.entities.Station
import com.google.gson.Gson
import javax.inject.Inject


class PreferencesHelper @Inject constructor(val context: Context, val gson: Gson) {
    private val PREFERENCES_KEY = "trainHomePreferences"

    private val KEY_HOME_STATION = "keyHomeStation"

    private fun getSharedPreferences() = context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE)

    fun saveHomeStation(station: Station) {
        val stringStation = gson.toJson(station)

        getSharedPreferences()
                .edit()
                .putString(KEY_HOME_STATION, stringStation)
                .apply()
    }

    fun getHomeStation(): Station {
        val stringStation = getSharedPreferences()
                .getString(KEY_HOME_STATION, "")

        return gson.fromJson(stringStation, Station::class.java)
    }

    /**@return true if the user has selected a home station. */
    fun homeStationSet() = getSharedPreferences().getString(KEY_HOME_STATION, "") != ""

}