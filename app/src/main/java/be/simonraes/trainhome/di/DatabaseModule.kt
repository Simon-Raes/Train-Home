package be.simonraes.trainhome.di

import android.arch.persistence.room.Room
import android.content.Context
import be.simonraes.trainhome.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideStationsDao(appDatabase: AppDatabase) = appDatabase.stationDao()

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "train_home").build()
}