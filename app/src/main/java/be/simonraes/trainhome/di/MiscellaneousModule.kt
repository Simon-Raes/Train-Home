package be.simonraes.trainhome.di

import be.simonraes.trainhome.rx.AppSchedulerProvider
import be.simonraes.trainhome.rx.SchedulerProvider
import com.google.gson.Gson

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

/**
 * Provides smaller utility dependencies that don't fit in any of the larger modules.
 */
@Module
internal class MiscellaneousModule {

    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }
}
