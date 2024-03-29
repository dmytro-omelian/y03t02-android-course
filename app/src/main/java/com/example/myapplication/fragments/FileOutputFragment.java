package com.example.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.myapplication.R;

public class FileOutputFragment extends Fragment {
    private static final String OUTPUT_ARG_PARAM = "No output provided";

    private TextView textView;
    private Button backButton;

    private String outputParam;

    public FileOutputFragment() {
    }

    private static ContentFragment.OpenFileInputCallback openFileInputCallback;

    public static FileOutputFragment newInstance(String outputParam, ContentFragment.OpenFileInputCallback callback) {
        FileOutputFragment fragment = new FileOutputFragment();
        openFileInputCallback = callback;
        Bundle args = new Bundle();
        args.putString(OUTPUT_ARG_PARAM, outputParam);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            outputParam = getArguments().getString(OUTPUT_ARG_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.file_output, container, false);

        textView = view.findViewById(R.id.fileOutput);
        backButton = view.findViewById(R.id.backButtonOnFileOutput);

        backButton.setOnClickListener(view1 -> {
            ContentFragment formFragment = new ContentFragment();
            formFragment.setOpenFileInputCallback(openFileInputCallback);

            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, formFragment)
                    .commit();
        });

        if (outputParam != null) {
            textView.setText(outputParam);
        }

        return view;
    }

}