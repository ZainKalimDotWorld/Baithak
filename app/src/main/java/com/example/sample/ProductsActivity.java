package com.example.sample;

import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.droidnet.DroidListener;
import com.droidnet.DroidNet;
import com.github.angads25.toggle.LabeledSwitch;
import com.github.angads25.toggle.interfaces.OnToggledListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ProductsActivity extends BaseActivity implements DroidListener {

    Button imageView2;
    private ArrayList<Item> items;
    private ArrayList<Item> items2;
    private RecyclerView recyclerView;
    SnapRecyclerAdapter adapter;
    SnapRecyclerAdapter2 adapter2;
    DroidNet mDroidNet;
    Boolean toggle;
    SweetAlertDialog pDialog;
    //    SwipeRefreshLayout mSwipeRefreshLayout;
    int products_intent,products_intent2;
    TextView textView1;
    public static LabeledSwitch swToggle;
    boolean lh;
    Boolean third_Value;
    String xyz;

    boolean onResumeCalled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        mDroidNet = DroidNet.getInstance();
        mDroidNet.addInternetConnectivityListener(this);


        items = new ArrayList<>();
        items2 = new ArrayList<>();

        textView1 = (TextView) findViewById(R.id.textView1234);

        products_intent = getIntent().getIntExtra("Products_Id", 0);
        Log.d("Products_Value", "" + products_intent);


        if (products_intent==0)
        {
            products_intent2=Main_Manu_Adapter.ji;
            Log.d("products_intent22", "" + products_intent2);

        }

//        1st to 2nd
        toggle = getIntent().getExtras().getBoolean("Toggle_Value");
        Log.d("Toggl_Values", "" + toggle);

        if (toggle == null) {

        } else {

        }

//        Boolean lovers = getIntent().getExtras().getBoolean("Third_Second");
//        Log.d("third_Value" , ""+lovers);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
//        mSwipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        swToggle = findViewById(R.id.iv_toggle);

        imageView2 = findViewById(R.id.imageView2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                finish();

                lh = swToggle.isOn();
                Intent intent = new Intent(ProductsActivity.this, Main_MenuScreen.class);
                intent.putExtra("Toggle_Value_Back", lh);
                startActivity(intent);
                finish();
            }
        });


        SnapHelper snapHelper = new GravitySnapHelper(Gravity.START);
        snapHelper.attachToRecyclerView(recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);


//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                updatemenu();
//            }
//        });


        swToggle.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here

                if (isOn) {
                    showdatainarabic();
                } else {

                    loadallimages();
                }
            }

        });


// if (toggle)
//{
//    showdatainarabic();
////    Toast.makeText(ProductsActivity.this, "Here2", Toast.LENGTH_SHORT).show();
//    swToggle.setOn(true);
//}
//
////else if (lovers)
////{
////
//////    Toast.makeText(ProductsActivity.this, "Toggle_Value6" + first_Value, Toast.LENGTH_SHORT).show();
////    swToggle.setOn(third_Value);
////    showdatainarabic();
////}
//
//
//        else
//        {
////            loadallimages();
//            swToggle.setOn(false);
//
//        }


//        if (toggle_back_from_toggle)
//        {
////            showdatainarabic();
//            showdatainarabic();
////            swToggle.setOn(true);
//        }
//

//
    }


    @Override
    protected void onResume() {
        super.onResume();
//        Toast.makeText(this, "Here2", Toast.LENGTH_SHORT).show();
//        Toast.makeText(ProductsActivity.this, "Heres11", Toast.LENGTH_SHORT).show();


        third_Value = getIntent().getBooleanExtra("Third_Second", false);
        Log.d("third_Value", "" + third_Value);


        if (toggle) {
            showdatainarabic();
            swToggle.setOn(toggle);
//            Toast.makeText(ProductsActivity.this, "Toggle_Value6" + third_Value, Toast.LENGTH_SHORT).show();
        } else if (third_Value) {

            showdatainarabic();
//            Toast.makeText(ProductsActivity.this, "Toggle_Value6" + toggle, Toast.LENGTH_SHORT).show();
            swToggle.setOn(third_Value);

        } else {
            loadallimages();
            swToggle.setOn(false);
        }


//        if(onResumeCalled)
//        {
//            Product_Detail product_detail=new Product_Detail();
//
//            if (product_detail.swToggle==null)
//            {
//                loadallimages();
//                swToggle.setOn(false);
//                Toast.makeText(ProductsActivity.this, "Heres12", Toast.LENGTH_SHORT).show();
//                onResumeCalled=true;
//
//            }
//
//            else if (product_detail.swToggle.isOn())
//            {
//                showdatainarabic();
//                swToggle.setOn(true);
//                Toast.makeText(ProductsActivity.this, "Heres13", Toast.LENGTH_SHORT).show();
//                onResumeCalled=true;
//
//            }
//
//            else
//            {
//                loadallimages();
//                Toast.makeText(ProductsActivity.this, "Here1", Toast.LENGTH_SHORT).show();
//                swToggle.setOn(false);
//                onResumeCalled=true;
//
//            }




    }

    public void onBackPressed() {
//        Intent intent = new Intent(Product_Detail.this, ProductsActivity.class);
//        finish();
//        startActivity(intent);
    }


    public void showdatainarabic()
    {

        adapter2 = new SnapRecyclerAdapter2(this, items2);
        adapter2.setDataList(items2);
        recyclerView.setAdapter(adapter2);


        textView1.setText("شهي يقفز في الفم. نحن نخدم العاطفة");
        pDialog = Utilss.showSweetLoader(ProductsActivity.this, SweetAlertDialog.PROGRESS_TYPE, "Fetching Data...");

//        com.android.volley.RequestQueue queue = Volley.newRequestQueue(ProductsActivity.this);
//        String url = "http://api.surveymenu.dwtdemo.com/api/ar/category/"+products_intent+"/products?pg=1";


        if (products_intent==0)
        {
            com.android.volley.RequestQueue queue = Volley.newRequestQueue(ProductsActivity.this);
            String url = "http://api.surveymenu.dwtdemo.com/api/ar/category/"+products_intent2+"/products?pg=1";
            StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url,
                    new com.android.volley.Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.d("Json-response1", response);

                            try {
                                JSONArray jsonArray =new JSONArray(response);

                                if (jsonArray.length()==0)
                                {
                                    pDialog = new SweetAlertDialog(ProductsActivity.this, SweetAlertDialog.ERROR_TYPE).setConfirmButton("OK" , new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {

                                            pDialog.dismiss();
                                        }
                                    });

                                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                    pDialog.setTitleText("No Data Found");
                                    pDialog.setCancelable(true);
                                    pDialog.show();
                                }

                                else
                                {
                                    items2.clear();

                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = new JSONObject(String.valueOf(jsonArray.get(i)));
                                        Item dataObject = new Item();

                                        dataObject.setID(jsonObject.getInt("ID"));
                                        dataObject.setCategory(jsonObject.getString("Category"));
                                        dataObject.setProduct(jsonObject.getString("Product"));
                                        dataObject.setDescription(jsonObject.getString("Description"));
                                        dataObject.setImage(jsonObject.getString("Image"));
                                        dataObject.setPrice(jsonObject.getLong("Price"));


                                        items2.add(dataObject);
                                        adapter2.notifyDataSetChanged();

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Utilss.hideSweetLoader(pDialog);
                                            }
                                        });
                                    }
                                }




                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }


                    }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Utilss.hideSweetLoader(pDialog);
                        }
                    });
                }
            }) {
                @Override
                public Map getHeaders() {
                    HashMap headers = new HashMap();
                    headers.put("Authorization", "Bearer "+MainActivity.value2);
                    return headers;
                }
            };

            stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(stringRequest);
        }


        else
        {
            com.android.volley.RequestQueue queue = Volley.newRequestQueue(ProductsActivity.this);
            String url = "http://api.surveymenu.dwtdemo.com/api/ar/category/"+products_intent+"/products?pg=1";
            StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url,
                    new com.android.volley.Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.d("Json-response2", response);

                            try {
                                JSONArray jsonArray =new JSONArray(response);

                                if (jsonArray.length()==0)
                                {
                                    pDialog = new SweetAlertDialog(ProductsActivity.this, SweetAlertDialog.ERROR_TYPE).setConfirmButton("OK" , new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {

                                            pDialog.dismiss();
                                        }
                                    });

                                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                    pDialog.setTitleText("No Data Found");
                                    pDialog.setCancelable(true);
                                    pDialog.show();
                                }

                                else
                                {
                                    items2.clear();

                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = new JSONObject(String.valueOf(jsonArray.get(i)));
                                        Item dataObject = new Item();

                                        dataObject.setID(jsonObject.getInt("ID"));
                                        dataObject.setCategory(jsonObject.getString("Category"));
                                        dataObject.setProduct(jsonObject.getString("Product"));
                                        dataObject.setDescription(jsonObject.getString("Description"));
                                        dataObject.setImage(jsonObject.getString("Image"));
                                        dataObject.setPrice(jsonObject.getLong("Price"));


                                        items2.add(dataObject);
                                        adapter2.notifyDataSetChanged();

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Utilss.hideSweetLoader(pDialog);
                                            }
                                        });
                                    }
                                }




                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }


                    }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Utilss.hideSweetLoader(pDialog);
                        }
                    });
                }
            }) {
                @Override
                public Map getHeaders() {
                    HashMap headers = new HashMap();
                    headers.put("Authorization", "Bearer "+MainActivity.value2);
                    return headers;
                }
            };

            stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(stringRequest);
        }


    }


//    private void updatemenu()
//    {
//        pDialog = Utilss.showSweetLoader(ProductsActivity.this, SweetAlertDialog.PROGRESS_TYPE, "Fetching Data...");
//        items2= new ArrayList<>();
//
//        com.android.volley.RequestQueue queue = Volley.newRequestQueue(ProductsActivity.this);
//        String url = "http://api.surveymenu.dwtdemo.com/api/en/category/"+products_intent+"/products?pg=1";
//        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url,
//                new com.android.volley.Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        Log.d("Json-response1", response);
//
//                        try {
//                            JSONArray jsonArray =new JSONArray(response);
//
//                            items.clear();
//
//                            for (int i = 0; i < jsonArray.length(); i++)
//                            {
//                                JSONObject jsonObject = new JSONObject(String.valueOf(jsonArray.get(i)));
//                                Item dataObject = new Item();
//
//                                dataObject.setID(jsonObject.getInt("ID"));
//                                dataObject.setCategory(jsonObject.getString("Category"));
//                                dataObject.setProduct(jsonObject.getString("Product"));
//                                dataObject.setDescription(jsonObject.getString("Description"));
//                                dataObject.setImage(jsonObject.getString("Image"));
//                                dataObject.setPrice(jsonObject.getLong("Price"));
//
//
//                                items2.add(dataObject);
////                                adapter.notifyDataSetChanged();
//
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        Utilss.hideSweetLoader(pDialog);
//                                    }
//                                });
//
//                            }
//
//
//                            adapter = new SnapRecyclerAdapter(ProductsActivity.this, items2);
//                            recyclerView.setLayoutManager(new LinearLayoutManager(ProductsActivity.this, LinearLayoutManager.HORIZONTAL, false));
//                            recyclerView.setAdapter(adapter);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//
//
//                }, new com.android.volley.Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Utilss.hideSweetLoader(pDialog);
//                    }
//                });
//            }
//        }) {
//            @Override
//            public Map getHeaders() {
//                HashMap headers = new HashMap();
//                headers.put("Authorization", "Bearer "+MainActivity.value2);
//                return headers;
//            }
//        };
//
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        queue.add(stringRequest);
//        mSwipeRefreshLayout.setRefreshing(false);
//
//    }










    public void loadallimages()
    {

        adapter = new SnapRecyclerAdapter(this, items);
        adapter.setDataList(items);
        recyclerView.setAdapter(adapter);

        textView1.setText("Deliciousness jumping into the mouth. We serve passion");
        pDialog = Utilss.showSweetLoader(ProductsActivity.this, SweetAlertDialog.PROGRESS_TYPE, "Fetching Data...");


        if (products_intent==0)
        {
            com.android.volley.RequestQueue queue = Volley.newRequestQueue(ProductsActivity.this);
            String url = "http://api.surveymenu.dwtdemo.com/api/en/category/"+products_intent2+"/products?pg=1";
            StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url,
                    new com.android.volley.Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.d("Json-response1", response);

                            try {
                                JSONArray jsonArray =new JSONArray(response);

                                if (jsonArray.length()==0)
                                {
                                    SweetAlertDialog pDialog = new SweetAlertDialog(ProductsActivity.this, SweetAlertDialog.ERROR_TYPE).setConfirmButton("OK" , new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {


                                        }
                                    });

                                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                    pDialog.setTitleText("No Data Found");
                                    pDialog.setCancelable(true);
                                    pDialog.show();
                                }

                                else
                                {
                                    items.clear();

                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = new JSONObject(String.valueOf(jsonArray.get(i)));
                                        Item dataObject = new Item();

                                        dataObject.setID(jsonObject.getInt("ID"));
                                        dataObject.setCategory(jsonObject.getString("Category"));
                                        dataObject.setProduct(jsonObject.getString("Product"));
                                        dataObject.setDescription(jsonObject.getString("Description"));
                                        dataObject.setImage(jsonObject.getString("Image"));
                                        dataObject.setPrice(jsonObject.getLong("Price"));


                                        items.add(dataObject);
                                        adapter.notifyDataSetChanged();

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Utilss.hideSweetLoader(pDialog);
                                            }
                                        });
                                    }
                                }




                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }


                    }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Utilss.hideSweetLoader(pDialog);
                        }
                    });
                }
            }) {
                @Override
                public Map getHeaders() {
                    HashMap headers = new HashMap();
                    headers.put("Authorization", "Bearer "+MainActivity.value2);
                    return headers;
                }
            };

            stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(stringRequest);
        }

        else
        {
            com.android.volley.RequestQueue queue = Volley.newRequestQueue(ProductsActivity.this);
            String url = "http://api.surveymenu.dwtdemo.com/api/en/category/"+products_intent+"/products?pg=1";
            StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url,
                    new com.android.volley.Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {







                            Log.d("Json-response1", response);

                            try {
                                JSONArray jsonArray =new JSONArray(response);

                                if (jsonArray.length()==0)
                                {
                                    SweetAlertDialog pDialog = new SweetAlertDialog(ProductsActivity.this, SweetAlertDialog.ERROR_TYPE).setConfirmButton("OK" , new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {


                                        }
                                    });

                                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                                    pDialog.setTitleText("No Data Found");
                                    pDialog.setCancelable(true);
                                    pDialog.show();
                                }

                                else
                                {
                                    items.clear();

                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jsonObject = new JSONObject(String.valueOf(jsonArray.get(i)));
                                        Item dataObject = new Item();

                                        dataObject.setID(jsonObject.getInt("ID"));
                                        dataObject.setCategory(jsonObject.getString("Category"));
                                        dataObject.setProduct(jsonObject.getString("Product"));
                                        dataObject.setDescription(jsonObject.getString("Description"));
                                        dataObject.setImage(jsonObject.getString("Image"));
                                        dataObject.setPrice(jsonObject.getLong("Price"));


                                        items.add(dataObject);
                                        adapter.notifyDataSetChanged();

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Utilss.hideSweetLoader(pDialog);
                                            }
                                        });
                                    }
                                }




                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }


                    }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Utilss.hideSweetLoader(pDialog);
                        }
                    });
                }
            }) {
                @Override
                public Map getHeaders() {
                    HashMap headers = new HashMap();
                    headers.put("Authorization", "Bearer "+MainActivity.value2);
                    return headers;
                }
            };

            stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue.add(stringRequest);
        }


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDroidNet.removeInternetConnectivityChangeListener(this);
    }

    @Override
    public void onInternetConnectivityChanged(boolean isConnected) {

        if (isConnected) {
            //do Stuff with internet
//            netIsOn();
        } else {
            //no internet

//            Toast.makeText(this, "Internet Off..!!", Toast.LENGTH_SHORT).show();

            SweetAlertDialog pDialog = new SweetAlertDialog(ProductsActivity.this, SweetAlertDialog.ERROR_TYPE).setConfirmButton("OK" , new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {

                    finishAffinity();
                }
            });

            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
            pDialog.setTitleText("Internet Not Found");
            pDialog.setCancelable(true);
            pDialog.show();
        }
    }
}
