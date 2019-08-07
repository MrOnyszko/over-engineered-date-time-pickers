package pl.gratitude.over.engineered.date_time.pickers.date

sealed class DatePickerState {
  var tag: DatePickerTag = DatePickerTag.Default
  object DatePickerOpen : DatePickerState()
  data class DateChosen(val year: Int, val month: Int, val dayOfMonth: Int) : DatePickerState()
  object DatePickerClose : DatePickerState()
}

data class DateChosen(val year: Int, val month: Int, val dayOfMonth: Int) {
  var tag: DatePickerTag = DatePickerTag.Default
}