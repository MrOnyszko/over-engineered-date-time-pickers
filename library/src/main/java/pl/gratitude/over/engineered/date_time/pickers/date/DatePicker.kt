package pl.gratitude.over.engineered.date_time.pickers.date

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import java.util.Calendar
import java.util.TimeZone

class DatePicker : DialogFragment() {

  private val viewModel: DatePickerViewModel by lazyDatePickerViewModel()

  private val onDateSetListener: DatePickerDialog.OnDateSetListener =
    DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
      dialog?.isShowing ?: return@OnDateSetListener

      viewModel.apply(year, month + 1, dayOfMonth)
    }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    viewModel.open()
  }

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    val calendar = Calendar.getInstance(TimeZone.getDefault()).apply {
      timeInMillis = System.currentTimeMillis()
    }
    val arg = viewModel.arg ?: DatePickerArg(
      year = calendar[Calendar.YEAR],
      month = calendar[Calendar.MONTH] + 1,
      dayOfMonth = calendar[Calendar.DAY_OF_MONTH]
    )
    return DatePickerDialog(requireContext(), onDateSetListener, arg.year, arg.month, arg.dayOfMonth)
  }

  override fun onDetach() {
    viewModel.close()
    super.onDetach()
  }

}

