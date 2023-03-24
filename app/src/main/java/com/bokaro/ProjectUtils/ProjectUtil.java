package com.bokaro.ProjectUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Asrar Ali on 21/07/2022.
 */

public class ProjectUtil {
    private static ProgressDialog progressDialog;
    public static Toast toast;

    public void showToast(Context context, String Message) {
        Toast.makeText(context, Message, Toast.LENGTH_SHORT).show();
    }

    public void showDialog(Context context, String Message, String Title) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setCancelable(false);
            progressDialog.setTitle(Title);
            progressDialog.setMessage(Message);
            progressDialog.show();
        }
    }

    public void dismissDialog(Context context) {
        try {
            if (progressDialog != null) {
                progressDialog.cancel();
                progressDialog.dismiss();
                progressDialog = null;
            }
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean hasPermissionInManifest(Activity activity, int requestCode, String permissionName) {
        if (ContextCompat.checkSelfPermission(activity,
                permissionName)
                != PackageManager.PERMISSION_GRANTED) {
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(activity,
                    new String[]{permissionName},
                    requestCode);
        } else {
            return true;
        }
        return false;
    }

    public static boolean hasPermissionInManifest(Context context, int requestCode, String permissionName) {
        if (ContextCompat.checkSelfPermission(context,
                permissionName)
                != PackageManager.PERMISSION_GRANTED) {
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{permissionName},
                    requestCode);
        } else {
            return true;
        }
        return false;
    }
    public static File commonDocumentDirPath(int type)
    {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String FolderName="AsrarAli";
        File dir = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
        {
            //dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + FolderName);
            dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/" + FolderName);
        }
        else
        {
            dir = new File(Environment.getExternalStorageDirectory() + "/" + FolderName);
        }

        // Make sure the path directory exists.
        if (!dir.exists())
        {
            // Make it, if it doesn't exit
            boolean success = dir.mkdirs();
            if (!success)
            {
                dir = null;
            }
        }
        /*Create a media file name*/

        File mediaFile;
        if (type == 1) {
            mediaFile = new File(dir.getPath() + File.separator +
                    "AsrarAli" + timeStamp + ".png");
            /*Log.e(TAG, "commonDocumentDirPath: "+dir.getPath() + File.separator +
                    "Asrar Ali" + timeStamp + ".png" );


            crashlytics.setCustomKey("File Path", "Test111 "+dir.getPath() + File.separator +
                    "Asrar Ali" + timeStamp + ".png");
            crashlytics.log(">>>>>>>>>>>>>>>>>>>>>>>>>>>");
            try{
                throw new NullPointerException();
            }catch (Exception e){
                FirebaseCrashlytics.getInstance().recordException(e);

            }*/
// imageName = Const.APP_NAME + timeStamp + ".png";
        } else {
            /*crashlytics.setCustomKey("File Path", "Test222 "+dir.getPath() + File.separator +
                    "Asrar Ali" + timeStamp + ".png");
            Log.e(TAG, "commonDocumentDirPath: "+dir.getPath() + File.separator +
                    "Asrar Ali" + timeStamp + ".png" );
            crashlytics.log(">>>>>>>>>>>>>>>>>>>>>>>>>>>");*/
            return null;
        }
        return mediaFile;
    }
}
