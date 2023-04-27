package com.example.deviceinfo.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.deviceinfo.R;
import com.example.deviceinfo.models.ListViewDataModel;

public class SystemPage extends CustomFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_system_page, container, false);

        addData();
        initRecyclerView(view, R.id.rv_system);
        return view;
    }

    @Override
    protected void addData() {
        super.addData();
        list.clear();
        list.add(new ListViewDataModel("Model name ", Build.MODEL));
        list.add(new ListViewDataModel("Brand name ", Build.BRAND));
        list.add(new ListViewDataModel("Device name ", Build.DEVICE));
        list.add(new ListViewDataModel("Display name ", Build.DISPLAY));
        list.add(new ListViewDataModel("Board name ", Build.BOARD));
        list.add(new ListViewDataModel("Bootloader name ", Build.BOOTLOADER));
        list.add(new ListViewDataModel("Manufacturer name ", Build.MANUFACTURER));
        list.add(new ListViewDataModel("FingerPrint name ", Build.FINGERPRINT));
        list.add(new ListViewDataModel("Hardware name ", Build.HARDWARE));
        list.add(new ListViewDataModel("Soc_model name ", Build.SOC_MODEL));
        list.add(new ListViewDataModel("Product name ", Build.PRODUCT));

    }

    public SystemPage() {
        // required empty public constructor.
    }
}

