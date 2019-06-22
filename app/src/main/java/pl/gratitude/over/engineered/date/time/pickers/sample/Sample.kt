package pl.gratitude.over.engineered.date.time.pickers.sample

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_sample.*
import org.threeten.bp.LocalTime
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter
import pl.gratitude.over.engineered.date_time.pickers.date.DatePickerViewModel
import pl.gratitude.over.engineered.date_time.pickers.date.lazyDatePickerViewModel
import pl.gratitude.over.engineered.date_time.pickers.time.TimePickerViewModel
import pl.gratitude.over.engineered.date_time.pickers.time.lazyTimePickerViewModel

class Sample : Fragment() {

  private val datePickerViewModel: DatePickerViewModel by lazyDatePickerViewModel()

  private val timePickerViewModel: TimePickerViewModel by lazyTimePickerViewModel()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View = inflater.inflate(R.layout.fragment_sample, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    date_text_input_edit_text.setOnClickListener {
      findNavController().navigate(SampleDirections.creatorToDatePicker())
    }

    time_text_input_edit_text.setOnClickListener {
      findNavController().navigate(SampleDirections.creatorToTimePicker())
    }

    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    val timeFormatter = if (DateFormat.is24HourFormat(requireContext())) {
      DateTimeFormatter.ofPattern("HH:mm")
    } else {
      DateTimeFormatter.ofPattern("HH:mm aa")
    }

    date_text_input_edit_text.setText(dateFormatter.format(OffsetDateTime.now(ZoneId.systemDefault())))
    time_text_input_edit_text.setText(timeFormatter.format(LocalTime.now(ZoneId.systemDefault())))

    datePickerViewModel.datePickerSimpleState.observe(requireActivity(), Observer { state ->
      val date = OffsetDateTime.of(state.year, state.month, state.dayOfMonth, 0, 0, 0, 0, ZoneOffset.UTC)
      val formattedDate = dateFormatter.format(date)
      date_text_input_edit_text.setText(formattedDate)
    })

    timePickerViewModel.timePickerSimpleState.observe(requireActivity(), Observer { state ->
      val date = LocalTime.of(state.hourOfDay, state.minute)
      val formattedTime = timeFormatter.format(date)
      time_text_input_edit_text.setText(formattedTime)
    })

  }
}