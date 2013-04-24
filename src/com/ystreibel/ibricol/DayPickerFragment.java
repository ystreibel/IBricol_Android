package com.ystreibel.ibricol;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;

public class DayPickerFragment extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		final CharSequence[] items = { "Aujourd'hui", "Ce soir", "Demain", "Demain soir", "Après demain", "Après demain soir" };
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Quand ?");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialogInterface, int item) {
				((EditText)getActivity().findViewById(R.id.editText2)).setText(items[item]);
				return;
			}
		});

		return builder.create();
	}
}
