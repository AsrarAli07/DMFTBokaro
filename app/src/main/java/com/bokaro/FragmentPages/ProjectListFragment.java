package com.bokaro.FragmentPages;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.bokaro.ActivityClasses.DashboardActivity;
import com.bokaro.AdapterClasses.DialogBlockAdapter;
import com.bokaro.AdapterClasses.DialogFilterBlockAdapter;
import com.bokaro.AdapterClasses.DialogFilterFinancialYearAdapter;
import com.bokaro.AdapterClasses.DialogFinancialYearAdapter;
import com.bokaro.AdapterClasses.ProjectListAdapter;
import com.bokaro.Interfacess.Consts;
import com.bokaro.R;
import com.bokaro.databinding.FragmentNewProjectBinding;
import com.bokaro.databinding.FragmentProjectListBinding;

import java.util.ArrayList;

public class ProjectListFragment extends Fragment {

    FragmentProjectListBinding binding;
    Context mContext;
    ProjectListAdapter projectListAdapter;
    DashboardActivity dashboardActivity;
    Dialog filterDialog,financialYearDialog,blockDialog;
    DialogFilterFinancialYearAdapter dialogFilterFinancialYearAdapter;
    DialogFilterBlockAdapter dialogFilterBlockAdapter;
    ArrayList<String>financialList=new ArrayList<>();
    ArrayList<String>blockList=new ArrayList<>();
    EditText etFinancialYear,etBlock;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProjectListBinding.inflate(inflater,container,false);
        mContext=getActivity();
        setData();
        init();
        return binding.getRoot();
    }
    public void setData() {
        financialList.add("2016-17");
        financialList.add("2017-18");
        financialList.add("2018-19");
        financialList.add("2019-20");
        financialList.add("2020-21");
        financialList.add("2021-22");
        financialList.add("2022-23");

        blockList.add("Block 1");
        blockList.add("Block 2");
        blockList.add("Block 3");
        blockList.add("Block 4");
        blockList.add("Block 5");
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
        ArrayList<String>arrayList=new ArrayList<>();
        arrayList.add("");
        arrayList.add("");
        arrayList.add("");
        arrayList.add("");
        arrayList.add("");
        arrayList.add("");
        arrayList.add("");
        projectListAdapter = new ProjectListAdapter(arrayList,mContext, ProjectListFragment.this);
        binding.rcList.setLayoutManager(new LinearLayoutManager(mContext));
        binding.rcList.setAdapter(projectListAdapter);

        binding.ivFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFilter(getActivity());
            }
        });
    }
    public void selectFilter(Activity activity){
        filterDialog = new Dialog(activity);
        filterDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        filterDialog.setCancelable(false);
        filterDialog.setContentView(R.layout.filter_dialog);
        filterDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        filterDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        filterDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        etFinancialYear = filterDialog.findViewById(R.id.etFinancialYear);
        etBlock = filterDialog.findViewById(R.id.etBlock);
        RelativeLayout ll_Main = filterDialog.findViewById(R.id.ll_Main);
        Button btnApply = filterDialog.findViewById(R.id.btnApply);
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterDialog.dismiss();
            }
        });
        etFinancialYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFinancialYear(getActivity());
            }
        });etBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectBlock(getActivity());
            }
        });
        ll_Main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filterDialog.dismiss();
            }
        });
        filterDialog.setOnKeyListener(new Dialog.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    filterDialog.dismiss();
                }
                return true;
            }
        });
        filterDialog.show();
    }
    public void selectFinancialYear(Activity activity){
        financialYearDialog = new Dialog(activity);
        financialYearDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        financialYearDialog.setCancelable(false);
        financialYearDialog.setContentView(R.layout.financial_year_dialog);
        financialYearDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        financialYearDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        financialYearDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        RecyclerView rcFinancialYear = financialYearDialog.findViewById(R.id.rcFinancialYear);
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rcFinancialYear.setLayoutManager(manager);
        dialogFilterFinancialYearAdapter = new DialogFilterFinancialYearAdapter(mContext,financialList,ProjectListFragment.this);
        rcFinancialYear.setAdapter(dialogFilterFinancialYearAdapter);
        RelativeLayout ll_Main = financialYearDialog.findViewById(R.id.ll_Main);
        ll_Main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                financialYearDialog.dismiss();
            }
        });
        financialYearDialog.setOnKeyListener(new Dialog.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    financialYearDialog.dismiss();
                }
                return true;
            }
        });
        financialYearDialog.show();
    }
    public void setFinancialYear(String string){
        etFinancialYear.setText(string);
        financialYearDialog.dismiss();
    }

    public void selectBlock(Activity activity){
        blockDialog = new Dialog(activity);
        blockDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        blockDialog.setCancelable(false);
        blockDialog.setContentView(R.layout.block_dialog);
        blockDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        blockDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        blockDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        RecyclerView rcBlock = blockDialog.findViewById(R.id.rcBlock);
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rcBlock.setLayoutManager(manager);
        dialogFilterBlockAdapter =new DialogFilterBlockAdapter(mContext,blockList,ProjectListFragment.this);
        rcBlock.setAdapter(dialogFilterBlockAdapter);
        RelativeLayout ll_Main = blockDialog.findViewById(R.id.ll_Main);
        ll_Main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blockDialog.dismiss();
            }
        });
        blockDialog.setOnKeyListener(new Dialog.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    blockDialog.dismiss();
                }
                return true;
            }
        });
        blockDialog.show();
    }

    public void setBlock(String string){
        etBlock.setText(string);
        blockDialog.dismiss();
    }

    public void changePage(){
        //Consts.NEW_PROJECT_TITLE="Edit Project";
        dashboardActivity.editProjectFragmetns();
    }
}