package pl.gratitude.over.engineered.date_time.pickers.date

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class DatePickerViewModelFactory : ViewModelProvider.Factory {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return DatePickerViewModel() as T
  }
}