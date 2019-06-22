package pl.gratitude.over.engineered.date_time.pickers.date

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class DatePickerArg(val year: Int, val month: Int, val dayOfMonth: Int) : Parcelable