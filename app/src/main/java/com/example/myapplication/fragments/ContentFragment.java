package com.example.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputEditText;

import java.io.FileInputStream;
import java.io.IOException;

public class ContentFragment extends Fragment {

    private EditText outputText;
    private TextInputEditText inputEditText;
    private Button buttonSubmit;
    private RadioButton yesRadioButton;
    private RadioButton noRadioButton;

    private Button openFileButton;

    private OpenFileInputCallback openFileInputCallback;

    public ContentFragment() {
        setRetainInstance(true);
    }

    public void setOpenFileInputCallback(OpenFileInputCallback callback) {
        openFileInputCallback = callback;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);

        inputEditText = view.findViewById(R.id.textInputEditText);
        buttonSubmit = view.findViewById(R.id.button);
        yesRadioButton = view.findViewById(R.id.radio_yes);
        noRadioButton = view.findViewById(R.id.radio_no);
        openFileButton = view.findViewById(R.id.open_answers);

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

        openFileButton.setOnClickListener(view2 -> {
            String text = "Empty text.";

            if (openFileInputCallback != null) {
                text = readFile(text, "content.txt");
            }

            FileOutputFragment fileOutputFragment = FileOutputFragment.newInstance(text);

            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fileOutputFragment)
                    .commit();
        });
        return view;
    }

    private String readFile(String text, String filename) {
        FileInputStream fin = null;
        try {
            fin = openFileInputCallback.openFileInputStream(filename);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            text = new String(bytes);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return text;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState); // Save necessary data into the outState bundle
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState); // Restore the fragment's state from the savedInstanceState bundle
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

    public interface OpenFileInputCallback {
        FileInputStream openFileInputStream(String fileName) throws IOException;

    }

}