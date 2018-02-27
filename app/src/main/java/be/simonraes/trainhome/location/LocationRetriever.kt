package be.simonraes.trainhome.location

import be.simonraes.trainhome.location.entities.LatLong
import com.google.android.gms.location.FusedLocationProviderClient
import io.reactivex.Single
import io.reactivex.subjects.SingleSubject
import javax.inject.Inject

/**
 * Class that wraps the Android Location API and returns a simple POJO (POKO) latlong object.
 */
class LocationRetriever @Inject constructor(val fusedLocationProviderClient: FusedLocationProviderClient) {

    fun currentLocation(): Single<LatLong> {

        val subject = SingleSubject.create<LatLong>()

        // todo permissions
        fusedLocationProviderClient.lastLocation
                .addOnSuccessListener {
                    it?.let{
                        subject.onSuccess(LatLong(it.latitude, it.longitude))
                    }
                }
                .addOnFailureListener(subject::onError)

        return subject
    }
}