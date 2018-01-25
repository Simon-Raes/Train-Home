package be.simonraes.trainhome

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import be.simonraes.trainhome.stations.StationsActivity


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(StationsActivity.createIntent(this))
    }
}
