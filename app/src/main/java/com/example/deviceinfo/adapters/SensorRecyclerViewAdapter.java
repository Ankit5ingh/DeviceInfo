package com.example.deviceinfo.adapters;

import static com.example.deviceinfo.fragments.SensorPage.sensorData;

import android.os.Bundle;

import com.example.deviceinfo.models.ListViewDataModel;

import java.util.List;

public class SensorRecyclerViewAdapter extends RecyclerViewAdapter{

    public String data;

    public SensorRecyclerViewAdapter(List<ListViewDataModel> data) {
        super(data);
    }

    public void sensorData(String data){
        this.data = data;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if(newData.get(position).getKey() == "Accelerometer"){
            holder.tvValue.setText(sensorData);
        }
    }
}
