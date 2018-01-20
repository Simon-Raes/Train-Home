package be.simonraes.trainhome.di

import android.app.Application
import android.content.Context
import be.simonraes.trainhome.TrainHomeApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by SimonRaes on 20/01/2018.
 */
@Module
class AppModule(val app: TrainHomeApplication) {

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideApplication() : Application = app

}