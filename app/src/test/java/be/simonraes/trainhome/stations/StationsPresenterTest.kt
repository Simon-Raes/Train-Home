package be.simonraes.trainhome.stations

import be.simonraes.trainhome.entities.Station
import be.simonraes.trainhome.rx.TestSchedulerProvider
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test

class StationsPresenterTest {

    @MockK
    lateinit var stationsDataManager: StationsDataManager

    @MockK
    lateinit var stationsView: StationsPresenter.StationsView

    lateinit var stationsPresenter: StationsPresenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        every { stationsDataManager.downloadAndSaveStations() } returns Completable.complete()
        every { stationsDataManager.getStations() } returns Flowable.just(listOf<Station>())

        stationsPresenter = StationsPresenter(stationsDataManager, TestSchedulerProvider())
        stationsPresenter.attachView(stationsView)
    }

    @Test
    fun downloadAndSaveStationsWhenStarting() {
        stationsPresenter.start()

        verify { stationsDataManager.downloadAndSaveStations() }
    }

    @Test
    fun loadStationsFromDatabaseWhenStarting() {
        stationsPresenter.start()

        verify { stationsDataManager.getStations() }
    }

}