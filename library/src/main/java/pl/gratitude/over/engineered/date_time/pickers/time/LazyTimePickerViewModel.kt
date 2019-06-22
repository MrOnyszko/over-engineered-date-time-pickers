package pl.gratitude.over.engineered.date_time.pickers.time

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders

fun Fragment.lazyTimePickerViewModel(): Lazy<TimePickerViewModel> {
  return lazy {
    ViewModelProviders.of(requireActivity(), TimePickerViewModelFactory()).get(TimePickerViewModel::class.java)
  }
}

fun FragmentActivity.lazyTimePickerViewModel(): Lazy<TimePickerViewModel> {
  return lazy {
    ViewModelProviders.of(this, TimePickerViewModelFactory()).get(TimePickerViewModel::class.java)
  }
}