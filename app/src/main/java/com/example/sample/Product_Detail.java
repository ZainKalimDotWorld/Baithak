package com.example.sample;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.droidnet.DroidListener;
import com.droidnet.DroidNet;
import com.github.angads25.toggle.LabeledSwitch;
import com.github.angads25.toggle.interfaces.OnToggledListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.viewpagerindicator.CirclePageIndicator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.example.sample.Utilss.hideSweetLoader;

public class Product_Detail extends BaseActivity implements DroidListener {


    private ArrayList<Product_Image> items2;
    private ArrayList<Item> items;

    ImageView imageView3;
    String statusCode;
    private boolean clicked = false;

    static LabeledSwitch swToggle;
    int index = 0;
    int ItemName;
    LinearLayout indicator;
    private RecyclerView recyclerView;
    SnapRecyclerAdapter_Details adapter;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    SweetAlertDialog pDialog, dialog2;
    SwipeRefreshLayout mSwipeRefreshLayout;
   Button imageView2;
    TextView textView_1, textView_2, textView_34,textView_3;
    String text1, text2,text3, Category_Nam,text4,text5,text14;
    int Category_Id;
    public static ViewPager viewPager;
//    ViewPager viewPager2;
 boolean lh;
    ImageView image;
    int product_id;
    JSONObject jsonObject ;
    private int dotscount;
    private ImageView imageView4,imageView5;
    DroidNet mDroidNet;
    CardView cardView_2;
    Boolean toggle2;

    String ACTION_INTENT="custom-message";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__detail);

        mDroidNet = DroidNet.getInstance();
        mDroidNet.addInternetConnectivityListener(this);

        Category_Nam = getIntent().getStringExtra("Category_name");
        Log.d("Category_nam", Category_Nam);


        Category_Id = getIntent().getIntExtra("Category_Id", 0);
        Log.d("Category_id", "" + Category_Id);

        Log.d("Category_Idss" , ""+Category_Id);
        swToggle = findViewById(R.id.iv_toggle);

        toggle2 = getIntent().getExtras().getBoolean("ValueBool2");
        Log.d("Toggl_Values" , ""+toggle2);

        textView_1 = findViewById(R.id.textView_1);
        textView_2 = findViewById(R.id.textView_2);
        textView_34 = findViewById(R.id.textView_34);
        textView_3 = findViewById(R.id.textView_3);

        imageView2 = findViewById(R.id.imageView2);
        cardView_2 = findViewById(R.id.cardView_2);

        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);



//        imageView3 = findViewById(R.id.imageView3);

//        imageView3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                if (clicked)        //if false
//                {
//                    clicked=false;
//                    loadotherdata();
//
//                }
//
//                else
//                {
//                    clicked=true;
//                    showdatainarabic();
//                }
//
////                clicked=true;
//
//            }
//
//
//        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean values = swToggle.isOn();
//                if (values)
//                {
//                    ProductsActivity.swToggle.setOn(values);
//                    Intent intent=new Intent(Product_Detail.this , ProductsActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//
//                else
//                {
//                    ProductsActivity.swToggle.setOn(false);
//                    Intent intent=new Intent(Product_Detail.this , ProductsActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//                Intent intent=new Intent(Product_Detail.this , ProductsActivity.class);
//                intent.putExtra("Third_Second" , swToggle.isOn());
//                Log.d("Toggle_Value3" , ""+swToggle.isOn());


                Intent intent = new Intent(Product_Detail.this , ProductsActivity.class);
                intent.putExtra("Third_Second" , swToggle.isOn());
                Log.d("Toggle_Value3" , ""+swToggle.isOn());
                startActivity(intent);

                finish();

            }
        });

        swToggle.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here

                    if (!isOn)
                    {
                        loadotherdata();
//                        showdatainarabic();
                    }
                    else {
                        showdatainarabic();

                    }
                }
        });







        if (toggle2)
        {

            showdatainarabic();
            swToggle.setOn(true);
        }

        else
        {
            loadotherdata();
            swToggle.setOn(false);
//            swToggle.(true);
//            swToggle.setLabelOn("EN");

        }








//        image = findViewById(R.id.image);


        items = new ArrayList<>();
        items2=new ArrayList<>();

//        pDialog = Utilss.showSweetLoader(Product_Detail.this, SweetAlertDialog.PROGRESS_TYPE, "Fetching Data...");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("custom-message"));


        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(Product_Detail.this,items2);
        viewPager.setAdapter(viewPagerAdapter);

        SnapHelper snapHelper = new GravitySnapHelper(Gravity.START);
        snapHelper.attachToRecyclerView(recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);

        adapter = new SnapRecyclerAdapter_Details(Product_Detail.this, items);
        adapter.setDataList(items);
        recyclerView.setAdapter(adapter);


        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewPager.setCurrentItem(viewPager.getCurrentItem()-1 , true);
            }
        });


        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewPager.setCurrentItem(viewPager.getCurrentItem()+1 , true);
            }
        });


//        loadotherdata();

    }

//    private void showdatainenglishdata()
//    {
//
//    }



    public void onBackPressed() {
//        Intent intent = new Intent(Product_Detail.this, ProductsActivity.class);
//        finish();
//        startActivity(intent);
    }


    private void showdatainarabic()
    {

        ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) cardView_2.getLayoutParams();
        marginParams.setMargins(960, marginParams.topMargin, marginParams.rightMargin, marginParams.bottomMargin);

        pDialog = Utilss.showSweetLoader(Product_Detail.this, SweetAlertDialog.PROGRESS_TYPE, "Fetching Data...");
        com.android.volley.RequestQueue queue = Volley.newRequestQueue(Product_Detail.this);
        String url = "http://api.surveymenu.dwtdemo.com/api/products/{id}?id=" + Category_Id;
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Json-response1", response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    product_id = jsonObject.getInt("ProductCategoryID");
                    text3 = jsonObject.getString("ProductDescAr");

                    text4 = jsonObject.getString("CategoryName");
                    text5 = jsonObject.getString("CategoryNameAr");

                    text1 = jsonObject.getString("ProductNameAr");

                    text2 = jsonObject.getString("Price");

                    textView_1.setText(text1);
                    textView_2.setText(" درهم : " + text2);
                    textView_3.setText(text3);

                    textView_34.setText(text5);

                    Log.d("ValueIsCategory" , Category_Nam);

                    JSONArray jsonArray = jsonObject.getJSONArray("ProductImages");

                    for (int i = 0; i < jsonArray.length(); i++)
                    {
                        JSONObject jsonObject2 = new JSONObject(String.valueOf(jsonArray.get(i)));
                        Product_Image dataObject = new Product_Image();

                        dataObject.setImage_Name(jsonObject2.getString("ImageName"));
                        dataObject.setImagePath(jsonObject2.getString("ImagePath"));
                        dataObject.setThumbnail(jsonObject2.getString("Thumbnail"));
                        items2.add(dataObject);
                    }


                    ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(Product_Detail.this,items2);
                    viewPager.setAdapter(viewPagerAdapter);



                    if (product_id == 0) {

                    } else {
                        com.android.volley.RequestQueue queue = Volley.newRequestQueue(Product_Detail.this);
                        String url = "http://api.surveymenu.dwtdemo.com/api/ar/category/"+product_id+"/products?pg=1";
                        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url,
                                new com.android.volley.Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {

                                        Log.d("Json-response2", response);

                                        try {
                                            JSONArray jsonArray = new JSONArray(response);

                                            if (jsonArray.length()==0)
                                            {

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
//                                adapter.notifyDataSetChanged();

                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            hideSweetLoader(pDialog);
                                                        }
                                                    });

                                                }


                                                adapter = new SnapRecyclerAdapter_Details(Product_Detail.this, items);
                                                recyclerView.setLayoutManager(new LinearLayoutManager(Product_Detail.this, LinearLayoutManager.HORIZONTAL, false));
                                                recyclerView.setAdapter(adapter);

                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {

                                                        Utilss.hideSweetLoader(pDialog);
                                                    }
                                                });
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
                                headers.put("Authorization", MainActivity.value2);
                                return headers;
                            }


                        };

                        queue.add(stringRequest);


                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }



            }


        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError error) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideSweetLoader(pDialog);

                        Log.d("Error_Response", error.toString());

                    }
                });
            }
        }) {
            @Override
            public Map getHeaders() {
                HashMap headers = new HashMap();
                headers.put("Authorization", MainActivity.value2);
                return headers;
            }


//            @Override
//            protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                statusCode = String.valueOf(response.statusCode);
//                Log.d("StatusCode3", statusCode);
//                //Handling logic
//                return super.parseNetworkResponse(response);
//            }

        };
        queue.add(stringRequest);
    }









    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent

            ItemName = intent.getIntExtra("ValueId", 0);
            Log.d("ItemName", "" + ItemName);

            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(Product_Detail.this, items2, ItemName);
            viewPager.setAdapter(viewPagerAdapter);

            if (ItemName == 0) {
                Toast.makeText(context, "Error in retreive", Toast.LENGTH_SHORT).show();
            } else {

                if (swToggle.isOn() && toggle2)
                {
                    com.android.volley.RequestQueue queue = Volley.newRequestQueue(Product_Detail.this);
                    String url = "http://api.surveymenu.dwtdemo.com/api/products/{id}?id=" +ItemName;

                    StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            try {

                                jsonObject = new JSONObject(response);
                                product_id = jsonObject.getInt("ProductCategoryID");
                                text3 = jsonObject.getString("ProductDescAr");
                                text1 = jsonObject.getString("ProductNameAr");


                                text4 = jsonObject.getString("CategoryName");
                                text5 = jsonObject.getString("CategoryNameAr");

                                text2 = jsonObject.getString("Price");

                                textView_1.setText(text1);
                                textView_2.setText(" درهم : " + text2);
                                textView_3.setText(text3);
                                textView_34.setText(text5);

//                            textView_34.setText(Category_Nam);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        hideSweetLoader(pDialog);
                                    }
                                });

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    },

                            new com.android.volley.Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(final VolleyError error) {

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Utilss.hideSweetLoader(pDialog);
                                            Log.d("Error_Response", error.toString());

                                        }
                                    });
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

                }









// && toggle2
else if (!swToggle.isOn())
                {

                    com.android.volley.RequestQueue queue = Volley.newRequestQueue(Product_Detail.this);
                    String url = "http://api.surveymenu.dwtdemo.com/api/products/{id}?id=" +ItemName;

                    StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            try {

                                jsonObject = new JSONObject(response);
                                product_id = jsonObject.getInt("ProductCategoryID");
                                text3 = jsonObject.getString("ProductDesc");
                                text1 = jsonObject.getString("ProductName");


                                text4 = jsonObject.getString("CategoryName");
                                text5 = jsonObject.getString("CategoryNameAr");

                                text2 = jsonObject.getString("Price");

                                textView_1.setText(text1);
                                textView_2.setText(" AED : " + text2);
                                textView_3.setText(text3);

                                textView_34.setText(text4);

//                            textView_34.setText(Category_Nam);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        hideSweetLoader(pDialog);
                                    }
                                });

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    },

                            new com.android.volley.Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(final VolleyError error) {

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Utilss.hideSweetLoader(pDialog);
                                            Log.d("Error_Response", error.toString());

                                        }
                                    });
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

                }



            }
        }
    };



    private void loadotherdata() {


        ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) cardView_2.getLayoutParams();
        marginParams.setMargins(60,
                marginParams.topMargin, 0, //notice only changing right margin
                marginParams.bottomMargin);


        pDialog = Utilss.showSweetLoader(Product_Detail.this, SweetAlertDialog.PROGRESS_TYPE, "Fetching Data...");
        com.android.volley.RequestQueue queue = Volley.newRequestQueue(Product_Detail.this);
        String url = "http://api.surveymenu.dwtdemo.com/api/products/{id}?id=" + Category_Id;
        StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("Json-response1", response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            product_id = jsonObject.getInt("ProductCategoryID");
                            text3 = jsonObject.getString("ProductDesc");
                            text1 = jsonObject.getString("ProductName");
                            text14 = jsonObject.getString("CategoryName");

                            text2 = jsonObject.getString("Price");

                            textView_1.setText(text1);
                            textView_2.setText(" AED : " + text2);
                            textView_3.setText(text3);

                            textView_34.setText(text14);



                            JSONArray jsonArray = jsonObject.getJSONArray("ProductImages");

                            for (int i = 0; i < jsonArray.length(); i++)
                            {
                                JSONObject jsonObject2 = new JSONObject(String.valueOf(jsonArray.get(i)));
                                Product_Image dataObject = new Product_Image();

                                dataObject.setImage_Name(jsonObject2.getString("ImageName"));
                                dataObject.setImagePath(jsonObject2.getString("ImagePath"));
                                dataObject.setThumbnail(jsonObject2.getString("Thumbnail"));
                                items2.add(dataObject);
                            }


                            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(Product_Detail.this,items2);
                            viewPager.setAdapter(viewPagerAdapter);

                            if (product_id == 0) {

                            } else {
                                com.android.volley.RequestQueue queue = Volley.newRequestQueue(Product_Detail.this);
                                String url = "http://api.surveymenu.dwtdemo.com/api/en/category/"+product_id+"/products?pg=1";
                                StringRequest stringRequest = new StringRequest(com.android.volley.Request.Method.GET, url,
                                        new com.android.volley.Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {

                                                Log.d("Json-response2", response);

                                                try {
                                                    JSONArray jsonArray = new JSONArray(response);

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
//                                adapter.notifyDataSetChanged();

                                                        runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                hideSweetLoader(pDialog);
                                                            }
                                                        });

                                                    }


                                                    adapter = new SnapRecyclerAdapter_Details(Product_Detail.this, items);
                                                    recyclerView.setLayoutManager(new LinearLayoutManager(Product_Detail.this, LinearLayoutManager.HORIZONTAL, false));
                                                    recyclerView.setAdapter(adapter);

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
                                                hideSweetLoader(pDialog);
                                            }
                                        });
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


                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }


                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError error) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideSweetLoader(pDialog);

                        Log.d("Error_Response", error.toString());

                    }
                });
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

            SweetAlertDialog pDialog = new SweetAlertDialog(Product_Detail.this, SweetAlertDialog.ERROR_TYPE).setConfirmButton("OK" , new SweetAlertDialog.OnSweetClickListener() {
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
