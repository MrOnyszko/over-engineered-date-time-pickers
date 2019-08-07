package pl.gratitude.over.engineered.date_time.pickers.time

sealed class TimePickerState {
  var tag: TimePickerTag = TimePickerTag.Default

  object TimePickerOpen : TimePickerState()
  data class TimeChosen(val hourOfDay: Int, val minute: Int, val is24Hour: Boolean = false) : TimePickerState()
  object TimePickerClose : TimePickerState()
}

data class TimeChosen(val hourOfDay: Int, val minute: Int, val is24Hour: Boolean = false) {
  var tag: TimePickerTag = TimePickerTag.Default
}