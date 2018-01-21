package be.simonraes.trainhome.di

import be.simonraes.trainhome.MainActivity
import be.simonraes.trainhome.stations.StationsActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, DatabaseModule::class, LocationModule::class]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(stationsActivity: StationsActivity)

}