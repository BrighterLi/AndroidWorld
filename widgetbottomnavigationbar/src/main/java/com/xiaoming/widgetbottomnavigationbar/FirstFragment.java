package com.xiaoming.widgetbottomnavigationbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


@SuppressLint("ValidFragment")
public class FirstFragment extends Fragment {
    private String context;
    private TextView mTextView;

    @SuppressLint("ValidFragment")
    public FirstFragment(String context) {
        this.context = context;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_first, container, false);
        mTextView = view.findViewById(R.id.txt_content);
        mTextView.setText(context);
        return view;
    }
}
