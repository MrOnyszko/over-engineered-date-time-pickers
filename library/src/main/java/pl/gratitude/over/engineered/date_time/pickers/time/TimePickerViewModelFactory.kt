package pl.gratitude.over.engineered.date_time.pickers.time

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class TimePickerViewModelFactory : ViewModelProvider.Factory {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return TimePickerViewModel() as T
  }
}