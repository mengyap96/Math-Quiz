package com.example.user.mathquiz;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RequestNameDialog extends AppCompatDialogFragment {

    EditText editText_name;
    private RequestNameDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_name,null);

        editText_name = (EditText) view.findViewById(R.id.editText_name);

        builder.setView(view)
                .setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = editText_name.getText().toString();
                        listener.applyTexts(name);
                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        listener = (RequestNameDialogListener)context;
    }

    public interface RequestNameDialogListener{
        void applyTexts(String name);
    }

}
