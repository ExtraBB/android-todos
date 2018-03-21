package com.example.brunodossantoscarvalhal.todo.Main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.brunodossantoscarvalhal.todo.R;

/**
 * Created by brunodossantoscarvalhal on 20/03/2018.
 */

public class HelpFragment extends Fragment {

    public static final String ARG_TEXT = "text";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.help_fragment, container, false);
        TextView tv = view.findViewById(R.id.help_text);
        tv.setText(getArguments().getString(ARG_TEXT));

        return view;
    }
}