package pl.gratitude.over.engineered.date_time.pickers.date

data class DatePickerArg(
  val year: Int,
  val month: Int,
  val dayOfMonth: Int,
  val tag: DatePickerTag = DatePickerTag.Default
)

sealed class DatePickerTag {
  object Default : DatePickerTag()
  abstract class FeatureTag : DatePickerTag()
}