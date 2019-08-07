package pl.gratitude.over.engineered.date_time.pickers.date

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DatePickerViewModel : ViewModel() {

  private val _datePickerState: MutableLiveData<DatePickerState> = MutableLiveData()

  val datePickerState: LiveData<DatePickerState>
    get() = _datePickerState

  private val _datePickerSimpleState: MutableLiveData<DateChosen> = MutableLiveData()

  val datePickerSimpleState: LiveData<DateChosen>
    get() = _datePickerSimpleState

  var arg: DatePickerArg? = null

  internal fun open() = _datePickerState.postValue(DatePickerState.DatePickerOpen.apply {
    tag = arg?.tag ?: DatePickerTag.Default
  })

  internal fun close() = _datePickerState.postValue(DatePickerState.DatePickerClose.apply {
    tag = arg?.tag ?: DatePickerTag.Default
  })

  internal fun apply(year: Int, month: Int, dayOfMonth: Int) {
    _datePickerState.postValue(DatePickerState.DateChosen(year, month, dayOfMonth).apply {
      tag = arg?.tag ?: DatePickerTag.Default
    })
    _datePickerSimpleState.postValue(DateChosen(year, month, dayOfMonth).apply {
      tag = arg?.tag ?: DatePickerTag.Default
    })
  }

}