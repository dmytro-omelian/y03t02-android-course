package com.example.myapplication.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.myapplication.R;
import com.example.myapplication.services.ReadFileService;
import com.example.myapplication.services.WriteFileService;
import com.google.android.material.textfield.TextInputEditText;

public class ContentFragment extends Fragment {

    private ReadFileService readFileService;
    private WriteFileService writeFileService;
    private TextInputEditText inputEditText;
    private Button buttonSubmit;
    private RadioButton yesRadioButton;
    private RadioButton noRadioButton;

    public ContentFragment() {
        setRetainInstance(true);
    }

    public ContentFragment(ReadFileService readFileService, WriteFileService writeFileService) {
        this();

        this.readFileService = readFileService;
        this.writeFileService = writeFileService;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);

        inputEditText = view.findViewById(R.id.textInputEditText);
        buttonSubmit = view.findViewById(R.id.button);
        yesRadioButton = view.findViewById(R.id.radio_yes);
        noRadioButton = view.findViewById(R.id.radio_no);

        buttonSubmit.setOnClickListener(view1 -> {

            String message = (inputEditText.getText() == null) ? null : inputEditText.getText().toString();

            message = String.format("Question: %s \nAnswer: %s", message,
                    getResponseFromCheckBox(yesRadioButton.isChecked(), noRadioButton.isChecked()));

            OutputFragment outputFragment = OutputFragment.newInstance(getOutputMessage(message));

            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, outputFragment)
                    .commit();
        });
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save necessary data into the outState bundle
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        // Restore the fragment's state from the savedInstanceState bundle
    }


    private String getOutputMessage(String message) {
        return (message == null) ? "No question provided" : message;
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

}