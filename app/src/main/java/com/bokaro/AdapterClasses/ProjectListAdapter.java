package com.bokaro.AdapterClasses;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bokaro.ActivityClasses.ProjectViewActivity;
import com.bokaro.FragmentPages.ProjectListFragment;
import com.bokaro.databinding.ProjectListDesignBinding;

import java.util.ArrayList;

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.MyViewHolder> {
    ArrayList<String>arrayList;
    Context mContext;
    ProjectListDesignBinding binding;
    ProjectListFragment projectListFragment;

    public ProjectListAdapter(ArrayList<String> arrayList, Context mContext,ProjectListFragment projectListFragment) {
        this.arrayList = arrayList;
        this.mContext = mContext;
        this.projectListFragment = projectListFragment;
    }

    @NonNull
    @Override
    public ProjectListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ProjectListDesignBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectListAdapter.MyViewHolder holder, int position) {
        holder.binding.tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projectListFragment.changePage();
            }
        });
        holder.binding.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             mContext.startActivity(new Intent(mContext, ProjectViewActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ProjectListDesignBinding binding;
        public MyViewHolder(ProjectListDesignBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
