package be.simonraes.trainhome.persistence.db

import android.arch.persistence.room.*
import be.simonraes.trainhome.entities.Station
import be.simonraes.trainhome.entities.Station.Companion.STATIONS_COLUMN_ID
import be.simonraes.trainhome.entities.Station.Companion.STATIONS_COLUMN_NAME
import be.simonraes.trainhome.entities.Station.Companion.STATIONS_TABLE
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface StationDao {

    @Query("SELECT * FROM $STATIONS_TABLE ORDER BY $STATIONS_COLUMN_NAME")
    fun getAll(): Flowable<List<Station>>

    @Query("SELECT * FROM $STATIONS_TABLE WHERE $STATIONS_COLUMN_NAME LIKE '%' || :name || '%' ORDER BY $STATIONS_COLUMN_NAME")
    fun getByName(name: String): Flowable<List<Station>>

    @Query("SELECT * FROM $STATIONS_TABLE ORDER BY $STATIONS_COLUMN_NAME")
    fun getAllAsSingle(): Single<List<Station>>

    @Query("SELECT * FROM $STATIONS_TABLE WHERE $STATIONS_COLUMN_ID = :id LIMIT 1")
    fun findById(id: String): Single<Station>

    @Query("SELECT * FROM $STATIONS_TABLE WHERE $STATIONS_COLUMN_ID IN (:ids)")
    fun findByIds(ids: List<String>): Single<List<Station>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(stations: List<Station>)

    @Delete
    fun delete(station: Station)
}