package com.example.deviceinfo.fragments;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deviceinfo.MainActivity;
import com.example.deviceinfo.R;
import com.example.deviceinfo.adapters.RecyclerViewAdapter;
import com.example.deviceinfo.adapters.SensorRecyclerViewAdapter;
import com.example.deviceinfo.models.ListViewDataModel;


public class SensorPage extends CustomFragment implements SensorEventListener {

    private SensorManager sensorManager;
    public static String sensorData;
    public SensorRecyclerViewAdapter _adapter;

    public SensorPage() {
        // required empty public constructor.
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_sensor_page, container, false);

        sensorManager = ContextCompat.getSystemService(requireContext(), SensorManager.class);
        addData();
        initRecyclerView(view, R.id.rv_sensor);

        return view;
    }

    @Override
    protected void initRecyclerView(View view, int recyclerViewId) {
        recyclerView = view.findViewById(recyclerViewId);
        _adapter = new SensorRecyclerViewAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(_adapter);
    }

    @Override
    protected void addData() {
        super.addData();
        list.clear();
        list.add(new ListViewDataModel("Accelerometer", "loading..."));
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
        }
    }

    private void getAccelerometer(SensorEvent event) {
        float[] values = event.values;
        // Movement
        float x = values[0];
        float y = values[1];
        float z = values[2];

        sensorData = "x: " + x + "y: " + y + "z: " + z;

        addData();
        _adapter.sensorData(sensorData);
        _adapter.setData(list);

        float accelerationSquareRoot = (x * x + y * y + z * z)
                / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
        if (accelerationSquareRoot >= 2) //
        {
            Toast.makeText(requireContext(), "accelerating", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onResume() {
        super.onResume();
        // register this class as a listener for the orientation and
        // accelerometer sensors
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}








