package be.simonraes.trainhome.stations

import be.simonraes.trainhome.api.ApiManager
import be.simonraes.trainhome.persistence.db.StationDao
import be.simonraes.trainhome.entities.Station
import be.simonraes.trainhome.entities.Stations
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class StationsDataManagerTest {

    @MockK
    private lateinit var apiManager: ApiManager
    @MockK
    private lateinit var stationsDao: StationDao

    @MockK
    private lateinit var station1: Station
    @MockK
    private lateinit var station2: Station

    private lateinit var stationsList: List<Station>
    private lateinit var stations: Stations

    private lateinit var stationsDataManager: StationsDataManager

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        stationsList = listOf(station1, station2)
        stations = Stations("1.0.0", "1111", stationsList)
        every { apiManager.stations() } returns Single.just(stations)

        stationsDataManager = StationsDataManager(apiManager, stationsDao)
    }

    @Test
    fun downloadStations() {
        stationsDataManager.downloadAndSaveStations()
                .test()

        verify { apiManager.stations() }
    }

    @Test
    fun saveStations() {
        stationsDataManager.downloadAndSaveStations()
                .test()

        verify { stationsDao.insertAll(stationsList) }
    }

}