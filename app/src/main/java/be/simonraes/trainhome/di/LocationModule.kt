package be.simonraes.trainhome.di

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocationModule {

    @Provides
    @Singleton
    fun provideLocationProvider(context: Context): FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(context)

}