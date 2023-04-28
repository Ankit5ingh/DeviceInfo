package com.example.deviceinfo.fragments;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.deviceinfo.R;

import java.util.Locale;


public class SensorPage extends CustomFragment implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private Sensor lightSensor;
    private TextView accelerometer_tv_x;
    private TextView accelerometer_tv_y;
    private TextView accelerometer_tv_z;
    private TextView light_lx;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_sensor_page, container, false);
        sensorManager = ContextCompat.getSystemService(requireContext(), SensorManager.class);
        assert sensorManager != null;
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        accelerometer_tv_x = view.findViewById(R.id.accelerometer_x_value);
        accelerometer_tv_y = view.findViewById(R.id.accelerometer_y_value);
        accelerometer_tv_z = view.findViewById(R.id.accelerometer_z_value);
        light_lx = view.findViewById(R.id.light_lx);
        return view;
    }



    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
        }
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            getLight(event);
        }
    }

    private void getLight(SensorEvent event) {
        float lx = event.values[0];
        light_lx.setText(String.format(Locale.ENGLISH, "%.3f", lx));
    }

    private void getAccelerometer(SensorEvent event) {
        float[] values = event.values;
        // Movement
        float x = values[0];
        float y = values[1];
        float z = values[2];
        accelerometer_tv_x.setText(String.format(Locale.ENGLISH, "%.5f", x));
        accelerometer_tv_y.setText(String.format(Locale.ENGLISH, "%.5f", y));
        accelerometer_tv_z.setText(String.format(Locale.ENGLISH, "%.5f", z));

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
        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    public SensorPage() {
        // required empty public constructor.
    }

    public void isInternetAvailable(Context context) {
        boolean isWifi = false;
        boolean isCell = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (networkCapabilities != null) {
                if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    isWifi = true;
                    Log.e("Network", "WIFI");
                } else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    isCell = true;
                    Log.e("Network", "CELLULAR");
                } else {
                    Log.e("Network", "ERROR");
                }
            }
        }

        WifiManager wifiManager = (WifiManager) requireActivity().getSystemService(Context.WIFI_SERVICE);

// Method to get the current connection info
        WifiInfo wInfo = wifiManager.getConnectionInfo();

// Extracting the information from the received connection info
        String ipAddress = Formatter.formatIpAddress(wInfo.getIpAddress());
        int linkSpeed = wInfo.getLinkSpeed();
        int networkID = wInfo.getNetworkId();
        String ssid = wInfo.getSSID();
        boolean hssid = wInfo.getHiddenSSID();
        String bssid = wInfo.getBSSID();

        Log.i("Network", "ipAddress: " + ipAddress + ", SSID: "+ ssid );
    }

}








