package com.example.deviceinfo.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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


public class BatteryPage extends CustomFragment {

    IntentFilter intentfilter;
    private int voltage;
    private String technology;
    private int level;
    private float temp = 0;
    private String charging = "loading...";
    private String powerSource;
    private String health = "loading...";
    private boolean broadCastCalled = false;
    private String changedPower;

    public BatteryPage() {
        // required empty public constructor.
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_battery_page, container, false);
        getData();
        addData();
        initRecyclerView(view, R.id.rv_battery);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        intentfilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        requireActivity().registerReceiver(broadcastreceiver, intentfilter);
    }

    private final BroadcastReceiver broadcastreceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            int batteryHealth = intent.getIntExtra(BatteryManager.EXTRA_HEALTH,-1);
            int batteryTemp = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);
            temp = ((float) batteryTemp) / 10;
            voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);
            technology = intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY);
            // Are we charging / charged?
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                                 status == BatteryManager.BATTERY_STATUS_FULL;
            if(isCharging) charging = "Charging";
            else charging = "Discharging";
            // How are we charging?
            int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
            boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

            if(usbCharge) powerSource = "USB";
            else if (acCharge) powerSource = "AC";
            else powerSource = "Battery";

            if(batteryHealth == BatteryManager.BATTERY_HEALTH_COLD)
                health = "Cold";
            if(batteryHealth == BatteryManager.BATTERY_HEALTH_DEAD)
                health = "Dead";
            if (batteryHealth == BatteryManager.BATTERY_HEALTH_GOOD)
                health = "Good";
            if(batteryHealth == BatteryManager.BATTERY_HEALTH_OVERHEAT)
                health = "Over Heat";
            if (batteryHealth == BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE)
                health = "Over Voltage";
            if (batteryHealth == BatteryManager.BATTERY_HEALTH_UNKNOWN)
                health = "Unknown";
            if (batteryHealth == BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE)
                health = "Failure";

            Log.i("Broadcast", "");
            if(!Objects.equals(powerSource, changedPower)) {
                addData();
                adapter.setData(list);
                broadCastCalled = true;
                changedPower = powerSource;
            }
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        broadCastCalled = false;
        requireActivity().unregisterReceiver(broadcastreceiver);
    }

    private void getData(){
        BatteryManager batteryManager = ContextCompat.getSystemService(requireContext(), BatteryManager.class);
        //battery health
        assert batteryManager != null;// Get the battery level
        level = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
    }

    @Override
    protected void addData() {
        super.addData();
        list.clear();
        list.add(new ListViewDataModel("Charging Status", charging));
        list.add(new ListViewDataModel("Power Source", powerSource));
        list.add(new ListViewDataModel("Battery Level", level + " %"));
        list.add(new ListViewDataModel("Battery Voltage", voltage + "mV"));
        list.add(new ListViewDataModel("Battery Technology", technology));
        list.add(new ListViewDataModel("Battery Health", health));
        list.add(new ListViewDataModel("Battery Temperature", temp + "°C"));
    }




}