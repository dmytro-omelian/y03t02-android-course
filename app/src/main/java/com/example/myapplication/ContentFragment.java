package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class ContentFragment extends Fragment {

    private Fragment outputFragment;
    public ContentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        Button button = view.findViewById(R.id.button);
        button.setOnClickListener(view1 -> {
            TextInputEditText inputEditText = view.findViewById(R.id.textInputEditText);
            String message = (inputEditText.getText() == null) ? null : inputEditText.getText().toString();

            outputFragment = OutputFragment.newInstance(getOutputMessage(message));

            assert getFragmentManager() != null;
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment, outputFragment, null);
            transaction.commit();
        });
        return view;
    }

    private String getOutputMessage(String message) {
        return (message == null) ? "Hello from OutputFragment!" : message;
    }

}