package com.bokaro.ActivityClasses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bokaro.FragmentPages.GeoTagFragment;
import com.bokaro.FragmentPages.HomeFragment;
import com.bokaro.FragmentPages.NewProjectFragment;
import com.bokaro.FragmentPages.ProjectListFragment;
import com.bokaro.Interfacess.Consts;
import com.bokaro.R;
import com.bokaro.UserDTOS.UserDTO;
import com.bokaro.databinding.ActivityDashboardBinding;
import com.bokaro.prefrences.SharedPrefrence;

public class DashboardActivity extends AppCompatActivity {
    ActivityDashboardBinding binding;
    Context mContext;
    private Handler mHandler;
    HomeFragment homeFragment = new HomeFragment();
    NewProjectFragment newProjectFragment = new NewProjectFragment();
    ProjectListFragment projectListFragment = new ProjectListFragment();
    GeoTagFragment geoTagFragment = new GeoTagFragment();
    public static final String TAG_MAIN = "main";
    public static final String TAG_HOME = "home";
    public static final String TAG_NEW_PROJECT = "newProject";
    public static final String TAG_PROJECT = "project";
    public static final String TAG_GEOTAG = "geotag";
    public static String CURRENT_TAG = TAG_MAIN;
    public static int navItemIndex = 1;
    private boolean shouldLoadHomeFragOnBackPress = true;
    boolean doubleBackToExitPressedOnce=false;
    private FrameLayout frame;
    private static final float END_SCALE = 0.8f;
    SharedPrefrence prefrence;
    UserDTO userDTO;
    LinearLayout llHome,llNewProject,llProjects,llGeoTag,llLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_dashboard);
        mContext=DashboardActivity.this;
        prefrence = SharedPrefrence.getInstance(mContext);
        userDTO = prefrence.getUserData(Consts.LOGIN_DATA);
        mHandler = new Handler();
        init();
        listners();
        homeFragmetns();
    }
    public void init(){
        llHome = findViewById(R.id.llHome);
        llNewProject = findViewById(R.id.llNewProject);
        llProjects = findViewById(R.id.llProjects);
        llGeoTag = findViewById(R.id.llGeoTag);
        llLogout = findViewById(R.id.llLogout);
        binding.drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
           @Override
           public void onDrawerSlide(View drawerView, float slideOffset) {

               // Scale the View based on current slide offset
               final float diffScaledOffset = slideOffset * (1 - END_SCALE);
               final float offsetScale = 1 - diffScaledOffset;
               binding.contentView.setScaleX(offsetScale);
               binding.contentView.setScaleY(offsetScale);

               // Translate the View, accounting for the scaled width
               final float xOffset = drawerView.getWidth() * slideOffset;
               final float xOffsetDiff = binding.contentView.getWidth() * diffScaledOffset / 2;
               final float xTranslation = xOffset - xOffsetDiff;
               binding.contentView.setTranslationX(xTranslation);
           }

           @Override
           public void onDrawerClosed(View drawerView) {}
        }
        );
    }
    public void openDrawer(){
        binding.drawerLayout.openDrawer(GravityCompat.START);
        //InputMethodManager inputMethodManager=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        //inputMethodManager.hideSoftInputFromInputMethod(v.getWindowToken(),0);
    }
    public void closeDrawer(){
        binding.drawerLayout.closeDrawers();
        //InputMethodManager inputMethodManager=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        //inputMethodManager.hideSoftInputFromInputMethod(v.getWindowToken(),0);
    }
    public void listners(){

        llHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeFragmetns();
            }
        });
        llNewProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navItemIndex = 7;
                CURRENT_TAG = TAG_NEW_PROJECT;
                projectFragmetns();
            }
        });
        llProjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navItemIndex = 8;
                CURRENT_TAG = TAG_PROJECT;
                projectListFragmetns();
            }
        });
        llGeoTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navItemIndex = 9;
                CURRENT_TAG = TAG_GEOTAG;
                geoTagFragmetns();
            }
        });
        llLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefrence.clearAllPreferences();
                Intent intent = new Intent(mContext, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                finish();
            }
        });
    }
    public void editProjectFragmetns(){
        closeDrawer();
        Consts.NEW_PROJECT_TITLE="Edit Project";
        navItemIndex = 2;
        CURRENT_TAG = TAG_NEW_PROJECT;
        loadHomeFragment(newProjectFragment, CURRENT_TAG);
    }
    public void homeFragmetns(){
        closeDrawer();
        navItemIndex = 1;
        CURRENT_TAG = TAG_HOME;
        loadHomeFragment(homeFragment, CURRENT_TAG);
    }
    public void projectFragmetns(){
        closeDrawer();
        Consts.NEW_PROJECT_TITLE="New Project";
        navItemIndex = 2;
        CURRENT_TAG = TAG_NEW_PROJECT;
        loadHomeFragment(newProjectFragment, CURRENT_TAG);
    }
    public void projectListFragmetns(){
        closeDrawer();
        navItemIndex = 3;
        CURRENT_TAG = TAG_PROJECT;
        loadHomeFragment(projectListFragment, CURRENT_TAG);
    }
    public void geoTagFragmetns(){
        closeDrawer();
        navItemIndex = 4;
        CURRENT_TAG = TAG_GEOTAG;
        loadHomeFragment(geoTagFragment, CURRENT_TAG);
    }
    public void loadHomeFragment(final Fragment fragment, final String TAG) {
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        try {
            if (mPendingRunnable != null) {
                mHandler.post(mPendingRunnable);

            }
            //drawerLayout.closeDrawers();
            invalidateOptionsMenu();
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
        }

    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        onWindowFocusChanged(true);
        /*if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
            return;
        }*/
        if (shouldLoadHomeFragOnBackPress) {
            if (navItemIndex != 1) {
                navItemIndex = 1;
                CURRENT_TAG = TAG_HOME;
                homeFragmetns();
                return;
            } else {
                if (doubleBackToExitPressedOnce) {
                    super.onBackPressed();
                    return;
                }

                this.doubleBackToExitPressedOnce = true;
                Toast.makeText(this, "Please click again to exit", Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce=false;
                    }
                }, 2000);
            }
        }
    }

}