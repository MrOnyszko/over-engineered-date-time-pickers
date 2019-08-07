package pl.gratitude.over.engineered.date_time.pickers.time

data class TimePickerArg(
  val hourOfDay: Int,
  val minute: Int,
  val is24Hour: Boolean = false,
  val tag: TimePickerTag = TimePickerTag.Default
)

sealed class TimePickerTag {
  object Default : TimePickerTag()
  abstract class FeatureTag : TimePickerTag()
}