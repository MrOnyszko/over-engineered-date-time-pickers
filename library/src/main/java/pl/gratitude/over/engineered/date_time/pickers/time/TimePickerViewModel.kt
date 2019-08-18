package pl.gratitude.over.engineered.date_time.pickers.time

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimePickerViewModel : ViewModel() {

  private val _timePickerState: MutableLiveData<TimePickerState> = MutableLiveData()

  val timePickerState: LiveData<TimePickerState>
    get() = _timePickerState

  private val _timePickerSimpleState: MutableLiveData<TimeChosen?> = MutableLiveData()

  val timePickerSimpleState: LiveData<TimeChosen?>
    get() = _timePickerSimpleState

  var arg: TimePickerArg? = null

  internal fun open() = _timePickerState.postValue(TimePickerState.TimePickerOpen.apply {
    tag = arg?.tag ?: TimePickerTag.Default
  })

  internal fun close() = _timePickerState.postValue(TimePickerState.TimePickerClose.apply {
    tag = arg?.tag ?: TimePickerTag.Default
  })

  internal fun apply(hourOfDay: Int, minute: Int, is24Hour: Boolean) {
    _timePickerState.postValue(TimePickerState.TimeChosen(hourOfDay, minute, is24Hour).apply {
      tag = arg?.tag ?: TimePickerTag.Default
    })
    _timePickerSimpleState.postValue(TimeChosen(hourOfDay, minute, is24Hour).apply {
      tag = arg?.tag ?: TimePickerTag.Default
    })
  }

  fun clear() {
    _timePickerSimpleState.postValue(null)
  }

}