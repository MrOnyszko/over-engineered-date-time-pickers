package pl.gratitude.over.engineered.date_time.pickers.time

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class TimePickerArg(val hourOfDay: Int, val minute: Int, val is24Hour: Boolean = false) : Parcelable