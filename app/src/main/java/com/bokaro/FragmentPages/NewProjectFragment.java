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
import android.widget.RelativeLayout;

import com.bokaro.ActivityClasses.DashboardActivity;
import com.bokaro.AdapterClasses.DialogAgencyAdapter;
import com.bokaro.AdapterClasses.DialogBlockAdapter;
import com.bokaro.AdapterClasses.DialogExpanseTypeAdapter;
import com.bokaro.AdapterClasses.DialogFinancialYearAdapter;
import com.bokaro.AdapterClasses.DialogGramPanchayatAdapter;
import com.bokaro.AdapterClasses.DialogSectorAdapter;
import com.bokaro.AdapterClasses.DialogStatusAdapter;
import com.bokaro.AdapterClasses.DialogVillageAdapter;
import com.bokaro.Interfacess.Consts;
import com.bokaro.R;
import com.bokaro.databinding.FragmentHomeBinding;
import com.bokaro.databinding.FragmentNewProjectBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class NewProjectFragment extends Fragment {
    FragmentNewProjectBinding binding;
    Context mContext;
    DashboardActivity dashboardActivity;
    String TAG=HomeFragment.class.getSimpleName();
    Dialog financialYearDialog,agencyDialog,blockDialog,gramPanchayatDialog,villageDialog,sectorDialog,statusDialog,expanseDialog;
    DialogFinancialYearAdapter dialogFinancialYearAdapter;
    DialogAgencyAdapter dialogAgencyAdapter;
    DialogBlockAdapter dialogBlockAdapter;
    DialogGramPanchayatAdapter dialogGramPanchayatAdapter;
    DialogVillageAdapter dialogVillageAdapter;
    DialogSectorAdapter dialogSectorAdapter;
    DialogStatusAdapter dialogStatusAdapter;
    DialogExpanseTypeAdapter dialogExpanseTypeAdapter;
    ArrayList<String>financialList=new ArrayList<>();
    ArrayList<String>agencyList=new ArrayList<>();
    ArrayList<String>blockList=new ArrayList<>();
    ArrayList<String>gramPanchayatList=new ArrayList<>();
    ArrayList<String>villageList=new ArrayList<>();
    ArrayList<String>sectorList=new ArrayList<>();
    ArrayList<String>statusList=new ArrayList<>();
    ArrayList<String>expanseTypeList=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNewProjectBinding.inflate(inflater,container,false);
        mContext=getActivity();
        init();
        setData();
        return binding.getRoot();
    }
    public void setData(){
        financialList.add("2016-17");
        financialList.add("2017-18");
        financialList.add("2018-19");
        financialList.add("2019-20");
        financialList.add("2020-21");
        financialList.add("2021-22");
        financialList.add("2022-23");

        agencyList.add("Agency 1");
        agencyList.add("Agency 2");
        agencyList.add("Agency 3");
        agencyList.add("Agency 4");
        agencyList.add("Agency 5");

        blockList.add("Block 1");
        blockList.add("Block 2");
        blockList.add("Block 3");
        blockList.add("Block 4");
        blockList.add("Block 5");

        gramPanchayatList.add("Gram Panchayat 1");
        gramPanchayatList.add("Gram Panchayat 2");
        gramPanchayatList.add("Gram Panchayat 3");
        gramPanchayatList.add("Gram Panchayat 4");
        gramPanchayatList.add("Gram Panchayat 5");

        villageList.add("Village 1");
        villageList.add("Village 2");
        villageList.add("Village 3");
        villageList.add("Village 4");
        villageList.add("Village 5");

        sectorList.add("Sector 1");
        sectorList.add("Sector 2");
        sectorList.add("Sector 3");
        sectorList.add("Sector 4");
        sectorList.add("Sector 5");

        statusList.add("Status 1");
        statusList.add("Status 2");
        statusList.add("Status 3");
        statusList.add("Status 4");
        statusList.add("Status 5");

        expanseTypeList.add("Expanse Type 1");
        expanseTypeList.add("Expanse Type 2");
        expanseTypeList.add("Expanse Type 3");
        expanseTypeList.add("Expanse Type 4");
        expanseTypeList.add("Expanse Type 5");
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dashboardActivity=(DashboardActivity) context;
    }
    public void init(){
        binding.tvTitle.setText(Consts.NEW_PROJECT_TITLE);
        binding.ivNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dashboardActivity.openDrawer();
            }
        });
        binding.etFinancialYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFinancialYear(getActivity());
            }
        });
        binding.etAgency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAgency(getActivity());
            }
        });
        binding.etBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectBlock(getActivity());
            }
        });
        binding.etGramPanchayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectGramPanchayat(getActivity());
            }
        });
        binding.etVillage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectVillage(getActivity());
            }
        });
        binding.etSector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectSector(getActivity());
            }
        });
        binding.etStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectStatus(getActivity());
            }
        });
        binding.etExpanseType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectExpanseType(getActivity());
            }
        });
    }
    public void setFinancialYear(String string){
        binding.etFinancialYear.setText(string);
        financialYearDialog.dismiss();
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
        dialogFinancialYearAdapter =new DialogFinancialYearAdapter(mContext,financialList,NewProjectFragment.this);
        rcFinancialYear.setAdapter(dialogFinancialYearAdapter);
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
    public void setAgency(String string){
        binding.etAgency.setText(string);
        agencyDialog.dismiss();
    }
    public void selectAgency(Activity activity){
        agencyDialog = new Dialog(activity);
        agencyDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        agencyDialog.setCancelable(false);
        agencyDialog.setContentView(R.layout.agency_dialog);
        agencyDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        agencyDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        agencyDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        RecyclerView rcAgency = agencyDialog.findViewById(R.id.rcAgency);
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rcAgency.setLayoutManager(manager);
        dialogAgencyAdapter =new DialogAgencyAdapter( mContext,agencyList,NewProjectFragment.this);
        rcAgency.setAdapter(dialogAgencyAdapter);
        RelativeLayout ll_Main = agencyDialog.findViewById(R.id.ll_Main);
        ll_Main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agencyDialog.dismiss();
            }
        });
        agencyDialog.setOnKeyListener(new Dialog.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    agencyDialog.dismiss();
                }
                return true;
            }
        });
        agencyDialog.show();
    }
    public void setBlock(String string){
        binding.etBlock.setText(string);
        blockDialog.dismiss();
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
        dialogBlockAdapter =new DialogBlockAdapter(mContext,blockList,NewProjectFragment.this);
        rcBlock.setAdapter(dialogBlockAdapter);
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
    public void setGramPanchayat(String string){
        binding.etGramPanchayat.setText(string);
        gramPanchayatDialog.dismiss();
    }
    public void selectGramPanchayat(Activity activity){
        gramPanchayatDialog = new Dialog(activity);
        gramPanchayatDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        gramPanchayatDialog.setCancelable(false);
        gramPanchayatDialog.setContentView(R.layout.panchayat_dialog);
        gramPanchayatDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        gramPanchayatDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        gramPanchayatDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        RecyclerView rcList = gramPanchayatDialog.findViewById(R.id.rcList);
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rcList.setLayoutManager(manager);
        dialogGramPanchayatAdapter =new DialogGramPanchayatAdapter(mContext,gramPanchayatList,NewProjectFragment.this);
        rcList.setAdapter(dialogGramPanchayatAdapter);
        RelativeLayout ll_Main = gramPanchayatDialog.findViewById(R.id.ll_Main);
        ll_Main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gramPanchayatDialog.dismiss();
            }
        });
        gramPanchayatDialog.setOnKeyListener(new Dialog.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    gramPanchayatDialog.dismiss();
                }
                return true;
            }
        });
        gramPanchayatDialog.show();
    }

    public void setVillage(String string){
        binding.etVillage.setText(string);
        villageDialog.dismiss();
    }
    public void selectVillage(Activity activity){
        villageDialog = new Dialog(activity);
        villageDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        villageDialog.setCancelable(false);
        villageDialog.setContentView(R.layout.village_dialog);
        villageDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        villageDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        villageDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        RecyclerView rcList = villageDialog.findViewById(R.id.rcList);
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rcList.setLayoutManager(manager);
        dialogVillageAdapter =new DialogVillageAdapter(mContext,villageList,NewProjectFragment.this);
        rcList.setAdapter(dialogVillageAdapter);
        RelativeLayout ll_Main = villageDialog.findViewById(R.id.ll_Main);
        ll_Main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                villageDialog.dismiss();
            }
        });
        villageDialog.setOnKeyListener(new Dialog.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    villageDialog.dismiss();
                }
                return true;
            }
        });
        villageDialog.show();
    }
    public void setSector(String string){
        binding.etSector.setText(string);
        sectorDialog.dismiss();
    }
    public void selectSector(Activity activity){
        sectorDialog = new Dialog(activity);
        sectorDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        sectorDialog.setCancelable(false);
        sectorDialog.setContentView(R.layout.sector_dialog);
        sectorDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        sectorDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        sectorDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        RecyclerView rcList = sectorDialog.findViewById(R.id.rcList);
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rcList.setLayoutManager(manager);
        dialogSectorAdapter =new DialogSectorAdapter(mContext,sectorList,NewProjectFragment.this);
        rcList.setAdapter(dialogSectorAdapter);
        RelativeLayout ll_Main = sectorDialog.findViewById(R.id.ll_Main);
        ll_Main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sectorDialog.dismiss();
            }
        });
        sectorDialog.setOnKeyListener(new Dialog.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    sectorDialog.dismiss();
                }
                return true;
            }
        });
        sectorDialog.show();
    }
    public void setStatus(String string){
        binding.etStatus.setText(string);
        statusDialog.dismiss();
    }
    public void selectStatus(Activity activity){
        statusDialog = new Dialog(activity);
        statusDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        statusDialog.setCancelable(false);
        statusDialog.setContentView(R.layout.status_dialog);
        statusDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        statusDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        statusDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        RecyclerView rcList = statusDialog.findViewById(R.id.rcList);
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rcList.setLayoutManager(manager);
        dialogStatusAdapter =new DialogStatusAdapter(mContext,statusList,NewProjectFragment.this);
        rcList.setAdapter(dialogStatusAdapter);
        RelativeLayout ll_Main = statusDialog.findViewById(R.id.ll_Main);
        ll_Main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                statusDialog.dismiss();
            }
        });
        statusDialog.setOnKeyListener(new Dialog.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    statusDialog.dismiss();
                }
                return true;
            }
        });
        statusDialog.show();
    }
    public void setExpanseType(String string){
        binding.etExpanseType.setText(string);
        expanseDialog.dismiss();
    }
    public void selectExpanseType(Activity activity){
        expanseDialog = new Dialog(activity);
        expanseDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        expanseDialog.setCancelable(false);
        expanseDialog.setContentView(R.layout.expanse_dialog);
        expanseDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        expanseDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        expanseDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        RecyclerView rcList = expanseDialog.findViewById(R.id.rcList);
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rcList.setLayoutManager(manager);
        dialogExpanseTypeAdapter =new DialogExpanseTypeAdapter(mContext,expanseTypeList,NewProjectFragment.this);
        rcList.setAdapter(dialogExpanseTypeAdapter);
        RelativeLayout ll_Main = expanseDialog.findViewById(R.id.ll_Main);
        ll_Main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expanseDialog.dismiss();
            }
        });
        expanseDialog.setOnKeyListener(new Dialog.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    expanseDialog.dismiss();
                }
                return true;
            }
        });
        expanseDialog.show();
    }
}