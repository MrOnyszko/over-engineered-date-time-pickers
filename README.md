# Over engineered Date Time Pickers

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
2. Define action using id `date_picker_nav_graph` or `date_picker_nav_graph`

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
        tools:layout="@layout/fragment_sample">

        <action
            android:id="@+id/sample_to_date_picker"
            app:destination="@id/date_time_picker_nav_graph" />

        <action
            android:id="@+id/sample_to_time_picker"
            app:destination="@id/time_picker_nav_graph" />

    </fragment>

</navigation>
```

## Interaction

0. Setup navigation 

```kotlin
  private fun navigation() {
    date_text_input_edit_text.setOnClickListener {
      findNavController().navigate(SampleDirections.sampleToDatePicker())
    }
    
    time_text_input_edit_text.setOnClickListener {
      findNavController().navigate(SampleDirections.sampleToTimePicker())
    }
  }
```

1. Add needed view models

```kotlin
  private val datePickerViewModel: DatePickerViewModel by lazyDatePickerViewModel()

  private val timePickerViewModel: TimePickerViewModel by lazyTimePickerViewModel()
```

2. Handle date picker

```kotlin
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
```

or simplified version

```kotlin
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
```

## License

```text
   Copyright [2019] [Sławomir Onyszko]

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.   
```