package pl.gratitude.over.engineered.date_time.pickers.date

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders

fun Fragment.lazyDatePickerViewModel(): Lazy<DatePickerViewModel> {
  return lazy {
    ViewModelProviders.of(requireActivity(), DatePickerViewModelFactory()).get(DatePickerViewModel::class.java)
  }
}

fun FragmentActivity.lazyDatePickerViewModel(): Lazy<DatePickerViewModel> {
  return lazy {
    ViewModelProviders.of(this, DatePickerViewModelFactory()).get(DatePickerViewModel::class.java)
  }
}