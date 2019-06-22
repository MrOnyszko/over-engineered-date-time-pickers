package pl.gratitude.over.engineered.date_time.pickers.time

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimePickerViewModel : ViewModel() {

  private val _timePickerState: MutableLiveData<TimePickerState> = MutableLiveData()

  val timePickerState: LiveData<TimePickerState>
    get() = _timePickerState

  private val _timePickerSimpleState: MutableLiveData<TimeChosen> = MutableLiveData()

  val timePickerSimpleState: LiveData<TimeChosen>
    get() = _timePickerSimpleState

  internal fun open() = _timePickerState.postValue(TimePickerState.TimePickerOpen)

  internal fun close() = _timePickerState.postValue(TimePickerState.TimePickerClose)

  internal fun apply(hourOfDay: Int, minute: Int, is24Hour: Boolean) {
    _timePickerState.postValue(TimePickerState.TimeChosen(hourOfDay, minute, is24Hour))
    _timePickerSimpleState.postValue(TimeChosen(hourOfDay, minute, is24Hour))
  }

}