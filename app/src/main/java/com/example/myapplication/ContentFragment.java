package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.Nullable;

public class ContentFragment extends Fragment {

    public ContentFragment() {
        super(R.layout.activity_main);
    }

    private String getResponseFromCheckBox(boolean answerIsYes, boolean answerIsNo) {
        if (!answerIsYes && !answerIsNo) {
            return "No Answer";
        }
        if (answerIsYes) {
            return "Yes";
        }
        return "No";
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button = view.findViewById(R.id.button);
        EditText input = view.findViewById(R.id.textInputEditText);
        RadioButton radioYes = view.findViewById(R.id.radio_yes);
        RadioButton radioNo = view.findViewById(R.id.radio_no);
        TextView output = view.findViewById(R.id.output);

        button.setOnClickListener(view1 -> {
            String message = String.format("Question: %s \nAnswer: %s", input.getText(),
                    getResponseFromCheckBox(radioYes.isChecked(), radioNo.isChecked()));
            output.setText(message);
        });
    }

}
