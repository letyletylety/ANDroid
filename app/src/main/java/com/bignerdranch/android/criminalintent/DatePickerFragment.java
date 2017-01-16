package com.bignerdranch.android.criminalintent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

/**
 * Created by lety02 on 2017. 1. 17..
 */

public class DatePickerFragment extends DialogFragment {
   @Override
   public Dialog onCreateDialog(Bundle savedInstanceState)
   {
      return new AlertDialog.Builder(getActivity())
              .setTitle(R.string.date_picker_title)
              .setPositiveButton(android.R.string.ok, null)
              .create();
   }
}
