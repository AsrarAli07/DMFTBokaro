package com.bokaro.ActivityClasses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bokaro.Interfacess.Consts;
import com.bokaro.Interfacess.Helper;
import com.bokaro.R;
import com.bokaro.UserDTOS.UserDTO;
import com.bokaro.databinding.ActivityLoginBinding;
import com.bokaro.http.HttpsRequest;
import com.bokaro.network.NetworkManager;
import com.bokaro.prefrences.SharedPrefrence;
import com.bokaro.utils.ProjectUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    Context mContext;
    ProjectUtils projectUtils;
    NetworkManager networkManager;
    ProgressDialog progressDialog;
    String TAG=LoginActivity.class.getSimpleName();
    SharedPrefrence prefrence;
    UserDTO userDTO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        mContext = LoginActivity.this;
        prefrence = SharedPrefrence.getInstance(mContext);
        init();
    }
    public void init(){
        projectUtils=new ProjectUtils();
        networkManager = new NetworkManager();
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please wait...");
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validation();
                startActivity(new Intent(mContext,DashboardActivity.class));
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
            }
        });
    }
    public void validation(){
        String email=binding.etEmail.getText().toString().trim();
        String password=binding.etPassword.getText().toString().trim();
        if (email.isEmpty()){
            binding.etEmail.setError("Please insert email");
            binding.etEmail.requestFocus();
        }else if (!projectUtils.isEmailValid(email)){
            binding.etEmail.setError("Please insert valid email");
            binding.etEmail.requestFocus();
        }else if (password.isEmpty()){
            binding.etPassword.setError("Please insert Password");
            binding.etPassword.requestFocus();
        }else{
            progressDialog.show();
            HashMap<String,String> hashMap=new HashMap<>();
            hashMap.put("user_email",email);
            hashMap.put("user_password",password);
            new HttpsRequest(Consts.LOGIN_URL,hashMap,mContext).stringPost(TAG, new Helper() {
                @Override
                public void backResponse(boolean flag, String msg, JSONObject response) {
                    progressDialog.dismiss();
                    projectUtils.showToast(mContext,msg);
                    if (flag){
                        try {
                            userDTO = new Gson().fromJson(response.getJSONObject("login").toString(),UserDTO.class);
                            prefrence.setUserData(userDTO,Consts.LOGIN_DATA);
                            prefrence.setRegisterValue(Consts.ISREGISTER,true);
                            startActivity(new Intent(mContext,DashboardActivity.class));
                            overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }else {
                        Log.e(TAG, "backResponse: ELSE" );
                        prefrence.setRegisterValue(Consts.ISREGISTER,false);
                    }
                }
            });
        }
    }
}