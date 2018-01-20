package be.simonraes.trainhome

import android.app.Application
import be.simonraes.trainhome.di.AppComponent
import be.simonraes.trainhome.di.AppModule
import be.simonraes.trainhome.di.DaggerAppComponent


fun thisWorksLikeAStatic() = "hi"

/**
 * Created by SimonRaes on 20/01/2018.
 */
class TrainHomeApplication : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}