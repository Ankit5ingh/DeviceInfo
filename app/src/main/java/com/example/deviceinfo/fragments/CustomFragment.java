package com.example.deviceinfo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deviceinfo.R;
import com.example.deviceinfo.adapters.RecyclerViewAdapter;
import com.example.deviceinfo.models.ListViewDataModel;

import java.util.ArrayList;
import java.util.List;

public class CustomFragment extends Fragment {
    protected RecyclerView recyclerView;
    protected List<ListViewDataModel> list;
    protected RecyclerViewAdapter adapter;

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

    protected void initRecyclerView(View view, int recyclerViewId) {
        recyclerView = view.findViewById(recyclerViewId);
        adapter = new RecyclerViewAdapter(list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    protected void addData(){
        list = new ArrayList<>();
        list.add(new ListViewDataModel("Key", "value"));
        list.add(new ListViewDataModel("Key", "value"));
        list.add(new ListViewDataModel("Key", "value"));
        list.add(new ListViewDataModel("Key", "value"));
        list.add(new ListViewDataModel("Key", "value"));
        list.add(new ListViewDataModel("Key", "value"));
    }


    public CustomFragment() {
        // required empty public constructor.
    }
}
