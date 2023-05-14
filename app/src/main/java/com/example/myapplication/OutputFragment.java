package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class OutputFragment extends Fragment {

    private static final String OUTPUT_ARG_PARAM = "Here will be your output";
    private String outputParam;
    public OutputFragment() {
    }

    public static OutputFragment newInstance(String outputParam) {
        OutputFragment fragment = new OutputFragment();
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
        View view = inflater.inflate(R.layout.fragment_output, container, false);

        TextView textView = view.findViewById(R.id.textViewOutput);
        textView.setText(outputParam);

        ContentFragment contentFragment = new ContentFragment();

        Button backButton = view.findViewById(R.id.backButton);
        backButton.setOnClickListener(view1 -> {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.outputFragment, contentFragment, null);
            transaction.commit();

            view1.setVisibility(View.VISIBLE);
        });
        return view;
    }
}