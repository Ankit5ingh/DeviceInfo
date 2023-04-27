package com.example.deviceinfo.fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.deviceinfo.R;
import com.example.deviceinfo.models.ListViewDataModel;

import java.util.Objects;

public class DevicePage extends CustomFragment {

    public DevicePage() {
        // required empty public constructor.
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_device_page, container, false);
        addData();
        initRecyclerView(view, R.id.rv_device);
        return view;
    }

    @Override
    protected void addData() {
        super.addData();
        list.clear();
        list.add(new ListViewDataModel("Ankit", ConnectivityManager.EXTRA_NETWORK_TYPE));
        list.add(new ListViewDataModel("Ankit", "Singh"));
        list.add(new ListViewDataModel("Ankit", "Singh"));
        list.add(new ListViewDataModel("Ankit", "Singh"));
        list.add(new ListViewDataModel("Ankit", "Singh"));
        list.add(new ListViewDataModel("Ankit", "Singh"));
    }
}

