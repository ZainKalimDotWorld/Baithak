package com.example.sample;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import dmax.dialog.SpotsDialog;


public class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Product_Image> items;
    private ArrayList<Product_Image> items2;
    SweetAlertDialog pdialog;

    int items_id;



    public ViewPagerAdapter(Context context , ArrayList<Product_Image> items) {
        this.context = context;
        this.items = items;


    }


    public ViewPagerAdapter(Context context , ArrayList<Product_Image> items , int items_id) {
        this.context = context;
        this.items = items;
        this.items_id = items_id;

        Log.d("items11" , ""+items_id);
        getapi();
    }

    private void getapi()
    {
        if (!((Activity) context).isFinishing()) {
            try {
                pdialog = Utilss.showSweetLoader(context, SweetAlertDialog.PROGRESS_TYPE, "Submitting...");

                items2 = new ArrayList<>();
                items.clear();
                com.android.volley.RequestQueue queue = Volley.newRequestQueue(context);
                String url = "http://api.surveymenu.dwtdemo.com/api/products/{id}?id=" +items_id;

                StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {

                        Log.d("ResponseMMM" , response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("ProductImages");

                            for (int i = 0; i < jsonArray.length(); i++)
                            {
                                items.clear();
                                JSONObject jsonObject2 = new JSONObject(String.valueOf(jsonArray.get(i)));
                                Product_Image dataObject = new Product_Image();

                                dataObject.setImage_Name(jsonObject2.getString("ImageName"));
                                dataObject.setImagePath(jsonObject2.getString("ImagePath"));
                                dataObject.setThumbnail(jsonObject2.getString("Thumbnail"));
                                items2.add(dataObject);
                            }
                            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(context,items2);
                            Product_Detail.viewPager.setAdapter(viewPagerAdapter);

                            ((Activity)context).runOnUiThread(new Runnable() {
                                @Override
                                public void run()
                                {
                                    //change View Data
                                    Utilss.hideSweetLoader(pdialog);
                                }
                            });



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },  new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(final VolleyError error) {

                        ((Activity)context).runOnUiThread(new Runnable() {
                            @Override
                            public void run()
                            {
                                //change View Data
                                Utilss.hideSweetLoader(pdialog);
                            }
                        });



                        Log.d("Error_Response", error.toString());
                    }
                }) {
                    @Override
                    public Map getHeaders() {
                        HashMap headers = new HashMap();
                        headers.put("Authorization", MainActivity.value2);
                        return headers;
                    }
                };
                queue.add(stringRequest);

            } catch (WindowManager.BadTokenException e) {
                Log.e("WindowManagerBad ", e.toString());
            }
        }



    }









    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

//        LocalBroadcastManager.getInstance(context).registerReceiver(mMessageReceiver, new IntentFilter("custom-message2"));
        final Product_Image item = items.get(position);

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.custom_layout, null);


        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        Picasso.with(context).load(item.getImagePath()).into(imageView);

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }


//    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//
////           int ItemName = intent.getIntExtra("ValueId2", 0);
////           Log.d("ItemName123", "" + ItemName);
//
//
//            if(ACTION_INTENT2.equals(intent.getAction()))
//            {
//                           int ItemName = intent.getIntExtra("ValueId2", 0);
//                           Log.d("ItemName123", "" + ItemName);
//
//                //DO
//            }
//
//
//        }
//    };



}
