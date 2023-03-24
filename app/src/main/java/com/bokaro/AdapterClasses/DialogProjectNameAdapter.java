package com.bokaro.AdapterClasses;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bokaro.FragmentPages.GeoTagFragment;
import com.bokaro.FragmentPages.NewProjectFragment;
import com.bokaro.databinding.AdapterFinancialYearViewBinding;
import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class DialogProjectNameAdapter extends RecyclerView.Adapter<DialogProjectNameAdapter.MyViewHolder> {
    Context mContext;
    ArrayList<String> arrayList;
    GeoTagFragment geoTagFragment;
    AdapterFinancialYearViewBinding binding;

    public DialogProjectNameAdapter(Context mContext, ArrayList<String> arrayList, GeoTagFragment geoTagFragment) {
        this.mContext = mContext;
        this.arrayList = arrayList;
        this.geoTagFragment = geoTagFragment;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        binding =  AdapterFinancialYearViewBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        //return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_financial_year_view,parent,false));
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.tvYear.setText(arrayList.get(position));
        holder.binding.tvYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                geoTagFragment.setProjectName(arrayList.get(position));
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        AdapterFinancialYearViewBinding binding;
        public MyViewHolder(AdapterFinancialYearViewBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}