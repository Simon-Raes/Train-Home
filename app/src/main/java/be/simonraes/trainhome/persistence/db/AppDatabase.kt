package be.simonraes.trainhome.persistence.db

import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database
import be.simonraes.trainhome.entities.Station

@Database(entities = arrayOf(Station::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun stationDao(): StationDao
}