package be.simonraes.trainhome.utils

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by SimonRaes on 30/01/2018.
 */
class DateFormatter @Inject constructor() {

    val dateFormat = SimpleDateFormat("hh:mm", Locale.getDefault())

    fun formatTimestamp(seconds: Long) : String {
        return dateFormat.format(Date(seconds * 1000))
    }

    fun secondsToFormattedDuration(durationSeconds: Long) : String {
        val hours = secondsToHours(durationSeconds).toString()
        val minutes = secondsToMinutes(durationSeconds % 3600).toString()
//            val seconds = remainingSeconds(durationSeconds).toString()

        return "$hours:$minutes"
    }
    fun secondsToHours(seconds: Long) = TimeUnit.SECONDS.toHours(seconds)
    fun secondsToMinutes(seconds: Long) = TimeUnit.SECONDS.toMinutes(seconds)
//        fun remainingSeconds(seconds: Long) = seconds % 60
}