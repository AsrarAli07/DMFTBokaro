package com.bokaro.FragmentPages;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bokaro.ActivityClasses.DashboardActivity;
import com.bokaro.R;
import com.bokaro.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    Context mContext;
    DashboardActivity dashboardActivity;
    String TAG=HomeFragment.class.getSimpleName();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        mContext=getActivity();
        init();
        return binding.getRoot();
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dashboardActivity=(DashboardActivity) context;
    }
    public void init(){
        binding.ivNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dashboardActivity.openDrawer();
            }
        });
        binding.llNewProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dashboardActivity.projectFragmetns();
            }
        });
        binding.llProjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dashboardActivity.projectListFragmetns();
            }
        });
        binding.llGeotag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dashboardActivity.geoTagFragmetns();
            }
        });
    }

}