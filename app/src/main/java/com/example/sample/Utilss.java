package com.example.sample;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.BatteryManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


import androidx.annotation.Keep;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.StringTokenizer;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.content.Context.INPUT_METHOD_SERVICE;


@Keep
public class Utilss {



    public static SweetAlertDialog showSweetLoader(Context context, int dialogtype, String title) {
        SweetAlertDialog pDialog = new SweetAlertDialog(context, dialogtype);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#0D5D98"));
        pDialog.setTitleText(title);
        pDialog.setCancelable(false);
        pDialog.show();

        return pDialog;
    }

    public static void hideSweetLoader(SweetAlertDialog pDialog) {
        pDialog.dismissWithAnimation();
    }



}
