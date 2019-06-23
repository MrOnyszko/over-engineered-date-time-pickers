# Over engineered Date Time Pickers

[![](https://jitpack.io/v/MrOnyszko/over-engineered-date-time-pickers.svg)](https://jitpack.io/#MrOnyszko/over-engineered-date-time-pickers)

## Synopsis

This is project of library that provides over engineered date time pickers.
This means that you can use DatePickerDialog and TimePickerDialog as a fully integrated screens with navigation and lifecycle architecture components.

## Motivation

Just playing around with architecture components.

## Dependency

Add this in your root `build.gradle` file (**not** your module `build.gradle` file):

```gradle
allprojects {
	repositories {
        maven { url "https://jitpack.io" }
    }
}
```

Then, add the library to your module `build.gradle`
```gradle
dependencies {
    compile 'com.github.MrOnyszko:over-engineered-date-time-pickers:latest.release.here'
}
```

## Usage

### Navigation

1. Include navigation graph eg. `date_picker_nav_graph` or `date_picker_nav_graph`

```xml
<?xml version="1.0" encoding="utf-8"?>
<navigation xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/nav_graph"
    app:startDestination="@+id/sample"
    tools:ignore="UnusedNavigation">

    <include app:graph="@navigation/date_picker_nav_graph" />
    <include app:graph="@navigation/time_picker_nav_graph" />

    <fragment
        android:id="@+id/sample"
        android:name="pl.gratitude.over.engineered.date.time.pickers.sample.Sample"
        android:label="@string/app_name"
        tools:layout="@layout/fragment_sample" />

</navigation>
```

## Interaction

0. Setup navigation 

Each graph has defined global action with an argument. Arguments are optional.

```kotlin
  private fun navigation() {
    date_text_input_edit_text.setOnClickListener {
      datePickerViewModel.arg = DatePickerArg(year = 2019, month = 6, dayOfMonth = 22)
      findNavController().navigate(R.id.date_picker_nav_graph)
    }

    time_text_input_edit_text.setOnClickListener {
      timePickerViewModel.arg = TimePickerArg(hourOfDay = 14, minute = 25, is24Hour = true)
      findNavController().navigate(R.id.time_picker_nav_graph)
    }
  }
```

1. Add needed view models using lazy method extensions defined in the library.

```kotlin
  private val datePickerViewModel: DatePickerViewModel by lazyDatePickerViewModel()

  private val timePickerViewModel: TimePickerViewModel by lazyTimePickerViewModel()
```

2. Handle date picker state

```kotlin
  private fun handleState() {
    datePickerViewModel.datePickerState.observe(this, Observer { state ->
      when (state) {
        DatePickerState.DatePickerOpen -> {
          Toast.makeText(requireContext(), "Date picker open", Toast.LENGTH_SHORT).show()
        }
        is DatePickerState.DateChosen -> {
          val date = OffsetDateTime.of(state.year, state.month + 1, state.dayOfMonth, 0, 0, 0, 0, ZoneOffset.UTC)
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
```

or simplified version

```kotlin
  private fun handleSimpleState() {
    datePickerViewModel.datePickerSimpleState.observe(requireActivity(), Observer { state ->
      val date = OffsetDateTime.of(state.year, state.month + 1, state.dayOfMonth, 0, 0, 0, 0, ZoneOffset.UTC)
      val formattedDate = dateFormatter.format(date)
      date_text_input_edit_text.setText(formattedDate)
    })

    timePickerViewModel.timePickerSimpleState.observe(requireActivity(), Observer { state ->
      val date = LocalTime.of(state.hourOfDay, state.minute)
      val formattedTime = timeFormatter.format(date)
      time_text_input_edit_text.setText(formattedTime)
    })
  }
```

## License

```text
    MIT License
    
    Copyright (c) [2019] [Sławomir Onyszko]
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
```