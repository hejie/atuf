package com.omd.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.omd.R;

public class TabFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_C = "content";

    public static TabFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString(ARG_C, content);
        TabFragment fragment = new TabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setTextSize(30);
        textView.setGravity(Gravity.CENTER);
        String content = getArguments().getString(ARG_C);
        textView.setText("Test\n\n" + content);
        textView.setBackgroundColor(0xFFececec);

        Button local = (Button) view.findViewById(R.id.local);
        local.setOnClickListener(this);
    }

    //打开本地bundle
    public void local(View view) {
        Intent intent = new Intent();
        intent.setClassName(view.getContext(), "com.omd.home.HomeActivity");
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        local(v);
    }
}
