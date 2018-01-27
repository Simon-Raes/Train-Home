package be.simonraes.trainhome.di

import be.simonraes.trainhome.home.HomeActivity
import be.simonraes.trainhome.stations.StationsActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class,
    NetworkModule::class,
    DatabaseModule::class,
    LocationModule::class,
    MiscellaneousModule::class]
)
interface AppComponent {
    fun inject(homeActivity: HomeActivity)
    fun inject(stationsActivity: StationsActivity)

}