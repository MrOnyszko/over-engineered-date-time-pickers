package pl.gratitude.over.engineered.date_time.pickers.time

sealed class TimePickerState {
  object TimePickerOpen : TimePickerState()
  data class TimeChosen(val hourOfDay: Int, val minute: Int, val is24Hour: Boolean = false) : TimePickerState()
  object TimePickerClose : TimePickerState()
}

data class TimeChosen(val hourOfDay: Int, val minute: Int, val is24Hour: Boolean = false)