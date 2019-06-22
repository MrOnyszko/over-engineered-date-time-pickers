package pl.gratitude.over.engineered.date_time.pickers.time

import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import java.util.Calendar
import java.util.TimeZone

class TimePicker : DialogFragment() {

  private val args: TimePickerArgs by navArgs()

  private val viewModel: TimePickerViewModel by lazyTimePickerViewModel()

  private val onDateSetListener: TimePickerDialog.OnTimeSetListener =
    TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
      dialog?.isShowing ?: return@OnTimeSetListener

      viewModel.apply(hourOfDay, minute, args.time?.is24Hour ?: DateFormat.is24HourFormat(requireContext()))
    }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    viewModel.open()
  }

  override fun onCreateDialog(savedInstanceState: Bundle?): TimePickerDialog {
    val calendar = Calendar.getInstance(TimeZone.getDefault()).apply {
      timeInMillis = System.currentTimeMillis()
    }
    val arg = args.time ?: TimePickerArg(
      hourOfDay = calendar[Calendar.HOUR_OF_DAY],
      minute = calendar[Calendar.MINUTE],
      is24Hour = DateFormat.is24HourFormat(requireContext())
    )
    return TimePickerDialog(requireContext(), onDateSetListener, arg.hourOfDay, arg.minute, arg.is24Hour)
  }

  override fun onDetach() {
    viewModel.close()
    super.onDetach()
  }

}