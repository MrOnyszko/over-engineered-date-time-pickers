package pl.gratitude.over.engineered.date.time.pickers.sample

import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_sample.*
import org.threeten.bp.LocalTime
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneId
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.DateTimeFormatter
import pl.gratitude.over.engineered.date_time.pickers.DateTimePickerNavGraphDirections
import pl.gratitude.over.engineered.date_time.pickers.TimePickerNavGraphDirections
import pl.gratitude.over.engineered.date_time.pickers.date.DatePickerArg
import pl.gratitude.over.engineered.date_time.pickers.date.DatePickerState
import pl.gratitude.over.engineered.date_time.pickers.date.DatePickerViewModel
import pl.gratitude.over.engineered.date_time.pickers.date.lazyDatePickerViewModel
import pl.gratitude.over.engineered.date_time.pickers.time.TimePickerArg
import pl.gratitude.over.engineered.date_time.pickers.time.TimePickerState
import pl.gratitude.over.engineered.date_time.pickers.time.TimePickerViewModel
import pl.gratitude.over.engineered.date_time.pickers.time.lazyTimePickerViewModel

class Sample : Fragment() {

  private val datePickerViewModel: DatePickerViewModel by lazyDatePickerViewModel()

  private val timePickerViewModel: TimePickerViewModel by lazyTimePickerViewModel()

  private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

  private val timeFormatter: DateTimeFormatter by lazy {
    if (DateFormat.is24HourFormat(requireContext())) {
      DateTimeFormatter.ofPattern("HH:mm")
    } else {
      DateTimeFormatter.ofPattern("HH:mm aa")
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View = inflater.inflate(R.layout.fragment_sample, container, false)

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    navigation()

    defaultInputsValue()

    handleState()

  }

  private fun handleState() {
    datePickerViewModel.datePickerState.observe(this, Observer { state ->
      when (state) {
        DatePickerState.DatePickerOpen -> {
          Toast.makeText(requireContext(), "Date picker open", Toast.LENGTH_SHORT).show()
        }
        is DatePickerState.DateChosen -> {
          val date = OffsetDateTime.of(state.year, state.month, state.dayOfMonth, 0, 0, 0, 0, ZoneOffset.UTC)
          val formattedDate = dateFormatter.format(date)
          date_text_input_edit_text.setText(formattedDate)
        }
        DatePickerState.DatePickerClose -> {
          Toast.makeText(requireContext(), "Date picker close", Toast.LENGTH_SHORT).show()
        }
      }
    })

    timePickerViewModel.timePickerState.observe(this, Observer { state ->
      when (state) {
        TimePickerState.TimePickerOpen -> {
          Toast.makeText(requireContext(), "Time picker open", Toast.LENGTH_SHORT).show()
        }
        is TimePickerState.TimeChosen -> {
          val date = LocalTime.of(state.hourOfDay, state.minute)
          val formattedTime = timeFormatter.format(date)
          time_text_input_edit_text.setText(formattedTime)
        }
        TimePickerState.TimePickerClose -> {
          Toast.makeText(requireContext(), "Time picker close", Toast.LENGTH_SHORT).show()
        }
      }
    })
  }

  private fun handleSimpleState() {
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

  private fun defaultInputsValue() {
    date_text_input_edit_text.setText(dateFormatter.format(OffsetDateTime.now(ZoneId.systemDefault())))
    time_text_input_edit_text.setText(timeFormatter.format(LocalTime.now(ZoneId.systemDefault())))
  }

  private fun navigation() {
    date_text_input_edit_text.setOnClickListener {
      findNavController().navigate(
        DateTimePickerNavGraphDirections.toDatePicker(DatePickerArg(year = 2019, month = 6, dayOfMonth = 22))
      )
    }

    time_text_input_edit_text.setOnClickListener {
      findNavController().navigate(
        TimePickerNavGraphDirections.toTimePicker(TimePickerArg(hourOfDay = 14, minute = 25, is24Hour = true))
      )
    }
  }
}