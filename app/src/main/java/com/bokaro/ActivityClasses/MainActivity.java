package com.bokaro.ActivityClasses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.bokaro.Interfacess.Consts;
import com.bokaro.R;
import com.bokaro.databinding.ActivityMainBinding;
import com.bokaro.prefrences.SharedPrefrence;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    public String TAG=MainActivity.class.getSimpleName();
    Context mContext;
    SharedPrefrence prefrence;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        mContext=MainActivity.this;
        prefrence = SharedPrefrence.getInstance(mContext);
        starttimer();
    }
    public void starttimer(){
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    if (prefrence.getRegisterValue(Consts.ISREGISTER)){
                        startActivity(new Intent(mContext, LoginActivity.class));
                        finish();
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    }else {
                        startActivity(new Intent(mContext, LoginActivity.class));
                        finish();
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    }

                }catch (Exception e){

                }


            }
        },3000);
    }
}