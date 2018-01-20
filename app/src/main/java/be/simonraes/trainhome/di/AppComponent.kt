package be.simonraes.trainhome.di

import be.simonraes.trainhome.MainActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        NetworkModule::class,
        LocationModule::class)
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)

}