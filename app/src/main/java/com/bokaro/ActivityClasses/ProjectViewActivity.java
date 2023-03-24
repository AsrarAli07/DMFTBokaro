package com.bokaro.ActivityClasses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.bokaro.R;
import com.bokaro.databinding.ActivityProjectViewBinding;

public class ProjectViewActivity extends AppCompatActivity {

    ActivityProjectViewBinding binding;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_project_view);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_project_view);
        mContext=ProjectViewActivity.this;
        init();
    }
    public void init(){
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.fadein,R.anim.fadeout);
    }
}