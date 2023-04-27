package com.example.deviceinfo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deviceinfo.R;
import com.example.deviceinfo.models.ListViewDataModel;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    protected List<ListViewDataModel> oldData;
    protected List<ListViewDataModel> newData;

    public RecyclerViewAdapter(List<ListViewDataModel> data) {
        oldData = data;
        newData = new ArrayList<>(data);
    }

    public void setData(List<ListViewDataModel> newData) {
        this.newData = new ArrayList<>(newData);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffCallback(oldData, newData));
        oldData.clear();
        oldData.addAll(newData);
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // set the data
        holder.tvKey.setText(newData.get(position).getKey());
        holder.tvValue.setText(newData.get(position).getValue());
    }

    @Override
    public int getItemCount() {
        return newData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvKey;
        public TextView tvValue;

        public MyViewHolder(View v) {
            super(v);
            tvKey = v.findViewById(R.id.tv_key);
            tvValue = v.findViewById(R.id.tv_value);
        }
    }

    private static class MyDiffCallback extends DiffUtil.Callback {
        private final List<ListViewDataModel> oldData;
        private final List<ListViewDataModel> newData;

        public MyDiffCallback(List<ListViewDataModel> oldData, List<ListViewDataModel> newData) {
            this.oldData = oldData;
            this.newData = newData;
        }

        @Override
        public int getOldListSize() {
            return oldData.size();
        }

        @Override
        public int getNewListSize() {
            return newData.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldData.get(oldItemPosition).equals(newData.get(newItemPosition));
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldData.get(oldItemPosition).equals(newData.get(newItemPosition));
        }
    }
}

