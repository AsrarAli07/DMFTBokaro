package com.bokaro.FragmentPages;

import static android.app.Activity.RESULT_OK;
import static android.os.Build.VERSION.SDK_INT;

import static com.bokaro.ProjectUtils.ProjectUtil.commonDocumentDirPath;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bokaro.ActivityClasses.DashboardActivity;
import com.bokaro.AdapterClasses.DialogFinancialYearAdapter;
import com.bokaro.AdapterClasses.DialogProjectNameAdapter;
import com.bokaro.Interfacess.Consts;
import com.bokaro.ProjectUtils.ImageCompression;
import com.bokaro.R;
import com.bokaro.databinding.FragmentGeoTagBinding;
import com.bokaro.prefrences.SharedPrefrence;
import com.bokaro.utils.MainFragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.easywaylocation.EasyWayLocation;
import com.example.easywaylocation.Listener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class GeoTagFragment extends Fragment implements Listener {

    FragmentGeoTagBinding binding;
    Context mContext;
    DashboardActivity dashboardActivity;
    String TAG=GeoTagFragment.class.getSimpleName();
    EasyWayLocation easyWayLocation;
    private String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION};
    Uri picUri;
    Bitmap bm;
    ImageCompression imageCompression;
    private static final int SELECTED_PIC = 1,SELECTED_PIC_CAM=7;
    int PICK_FROM_CAMERA = 1, PICK_FROM_GALLERY = 2,CROP_CAMERA_IMAGE = 3,CROP_GALLERY_IMAGE = 4,SELECT_VIDEO_GALLERY=5,SELECT_VIDEO_CAMERA=6,LanguagePosition=0;
    String pathOfImage="";
    SharedPrefrence prefrence;
    Dialog projectDialog;
    ArrayList<String>projectList=new ArrayList<>();
    DialogProjectNameAdapter dialogProjectNameAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_geo_tag, container, false);
        binding = FragmentGeoTagBinding.inflate(inflater,container,false);
        mContext=getActivity();
        prefrence=SharedPrefrence.getInstance(mContext);
        easyWayLocation = new EasyWayLocation(mContext, false,false,this);
        setData();
        init();
        return binding.getRoot();
    }
    public void setData(){
        projectList.add("Project 1");
        projectList.add("Project 2");
        projectList.add("Project 3");
        projectList.add("Project 4");
        projectList.add("Project 5");
        projectList.add("Project 6");
        projectList.add("Project 7");
        projectList.add("Project 8");
        projectList.add("Project 9");
        projectList.add("Project 10");
        projectList.add("Project 11");
        projectList.add("Project 12");
        projectList.add("Project 13");
        projectList.add("Project 14");
        projectList.add("Project 15");
        projectList.add("Project 16");
        projectList.add("Project 17");
        projectList.add("Project 18");
        projectList.add("Project 19");
        projectList.add("Project 20");
        projectList.add("Project 21");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dashboardActivity=(DashboardActivity) context;
    }

    private void init() {
        binding.ivNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dashboardActivity.openDrawer();
            }
        });
        binding.ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //checkPermission();
                checkPermission1();

            }
        });
        binding.etProjectName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectProjectName(getActivity());
            }
        });
    }
    public static boolean hasPermissions(Context context, String... permissions) {
        if (SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public void checkPermission1(){
        if (SDK_INT >= Build.VERSION_CODES.R) {
            if (Environment.isExternalStorageManager()) {
                imageSelection();
            } else { //request for the permission
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                Uri uri = Uri.fromParts("package", mContext.getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
        } else {
            imageSelection();
        }
    }

    public void imageSelection(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file=new File("");
        //file = getOutputMediaFile(1);
        file = commonDocumentDirPath(1);
        //Log.e(TAG, "onClick22: "+file.toString());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (SDK_INT >= Build.VERSION_CODES.N) {
            picUri = FileProvider.getUriForFile(mContext.getApplicationContext(),  "com.bokaro.fileprovider", file);
        } else {
            picUri = Uri.fromFile(file); // create
        }

        //SharedPref.setValue(Constants.IMAGE_URI_CAMERA, picUri.toString());
        prefrence.setValue(Consts.IMAGE_URI_CAMERA,picUri.toString());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, picUri); // set the image file
        startActivityForResult(intent, PICK_FROM_CAMERA);
    }

    @Override
    public void locationOn() {
        Toast.makeText(mContext, "Location ON", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!hasPermissions(mContext, permissions)) {
            Log.e(TAG, "onClick: IF tvGallery" );
            final AlertDialog.Builder builder=new AlertDialog.Builder(mContext);
            builder/*.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })*/.setPositiveButton("Setting", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                    Uri.fromParts("package", mContext.getPackageName(), null));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    }).setMessage("To use this page you need to provide all permission from setting")
                    .setCancelable(false)
                    .show();
        }else {
            easyWayLocation.startLocation();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        easyWayLocation.endUpdates();

    }

    @Override
    public void currentLocation(Location location) {
        /*StringBuilder data = new StringBuilder();
        data.append(location.getLatitude());
        data.append(" , ");
        data.append(location.getLongitude());
        latLong.setText(data);
        getLocationDetail.getAddress(location.getLatitude(), location.getLongitude(), "xyz");*/
        binding.tvLongitude.setText("Longitude : "+location.getLongitude());
        binding.tvLatitude.setText("Latitude : "+location.getLatitude());
    }

    @Override
    public void locationCancelled() {
        Toast.makeText(mContext, "Location Cancelled", Toast.LENGTH_SHORT).show();

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CROP_CAMERA_IMAGE) {
            if (data != null) {
                picUri = Uri.parse(data.getExtras().getString("resultUri"));
                try {
                    pathOfImage = picUri.getPath();
                    imageCompression = new ImageCompression(mContext);
                    imageCompression.execute(pathOfImage);
                    imageCompression.setOnTaskFinishedEvent(new ImageCompression.AsyncResponse() {
                        @Override
                        public void processFinish(String imagePath) {
                            pathOfImage=imagePath;
                            Glide.with(mContext).load("file://" + imagePath)
                                    .thumbnail(0.5f)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .into(binding.ivImage);
                            /*fileHashMap.put(Consts.IMAGE,new File(imagePath));
                            isImageNotSelectet=false;*/
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if (requestCode == CROP_GALLERY_IMAGE) {
            if (data != null) {
                picUri = Uri.parse(data.getExtras().getString("resultUri"));
                try {
                    bm = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), picUri);
                    pathOfImage = picUri.getPath();
                    imageCompression = new ImageCompression(mContext);
                    imageCompression.execute(pathOfImage);
                    imageCompression.setOnTaskFinishedEvent(new ImageCompression.AsyncResponse() {
                        @Override
                        public void processFinish(String imagePath) {
                            Log.e(TAG, "processFinish: "+imagePath );
                            pathOfImage=imagePath;
                            Glide.with(mContext).load("file://" + imagePath)
                                    .thumbnail(0.5f)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .into(binding.ivImage);
                            //isImageAvailable=true;
                            //prefrence.setSharedPreferences(Constants.USER_PROFILE_PIC, "file://" + imagePath);
                            //fileHashMap.put("product_image",new File(imagePath));
                            //updatePicture(new File(imagePath));
                            //prefrence.setTempImage("LocalImage","file://" + imagePath);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if (requestCode == PICK_FROM_CAMERA && resultCode == RESULT_OK) {
            if (picUri != null) {
                picUri = Uri.parse(prefrence.getValue(Consts.IMAGE_URI_CAMERA));
                startCropping(picUri, CROP_CAMERA_IMAGE);
            } else {
                picUri = Uri.parse(prefrence.getValue(Consts.IMAGE_URI_CAMERA));
                startCropping(picUri, CROP_CAMERA_IMAGE);
            }
        }

        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
            try {
                Uri tempUri = data.getData();
                Log.e("front tempUri", "" + tempUri);
                if (tempUri != null) {
                    startCropping(tempUri, CROP_GALLERY_IMAGE);
                } else {

                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }


    }
    public void startCropping(Uri uri, int requestCode) {
        Intent intent = new Intent(mContext, MainFragment.class);
        intent.putExtra("imageUri", uri.toString());
        intent.putExtra("requestCode", requestCode);
        startActivityForResult(intent, requestCode);
    }
    public void setProjectName(String string){
        binding.etProjectName.setText(string);
        projectDialog.dismiss();
    }
    public void selectProjectName(Activity activity){
        projectDialog = new Dialog(activity);
        projectDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        projectDialog.setCancelable(false);
        projectDialog.setContentView(R.layout.project_dialog);
        projectDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        projectDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        projectDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        RecyclerView rcList = projectDialog.findViewById(R.id.rcList);
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rcList.setLayoutManager(manager);
        dialogProjectNameAdapter =new DialogProjectNameAdapter(mContext,projectList,GeoTagFragment.this);
        rcList.setAdapter(dialogProjectNameAdapter);
        RelativeLayout ll_Main = projectDialog.findViewById(R.id.ll_Main);
        ll_Main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projectDialog.dismiss();
            }
        });
        projectDialog.setOnKeyListener(new Dialog.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                                 KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    projectDialog.dismiss();
                }
                return true;
            }
        });
        projectDialog.show();
    }
}