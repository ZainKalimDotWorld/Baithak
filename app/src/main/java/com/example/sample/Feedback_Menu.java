package com.example.sample;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.github.angads25.toggle.LabeledSwitch;
import com.github.angads25.toggle.interfaces.OnToggledListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Feedback_Menu extends AppCompatActivity {

    EditText customer_info,customer_info2;
    Button home,next;
    String email_validation,contact_validation;
    SweetAlertDialog pdialog;
    TextView textView;

    static   LabeledSwitch swToggle;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback__menu);


        Window window = this.getWindow();
// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(Feedback_Menu.this,R.color.colorback));


        customer_info = findViewById(R.id.customer_info);
        customer_info2 = findViewById(R.id.customer_info2);

//        customer_info.setHint("Email Address");
//        customer_info2.setHint("Contact Number");

        home=findViewById(R.id.home);
        next=findViewById(R.id.next);


        textView= findViewById(R.id.textView);

        swToggle = findViewById(R.id.iv_toggle);


//        customer_info.setHint("Email Address");
//        customer_info2.setHint("Contact Number");

        swToggle.setOnToggledListener(new OnToggledListener() {
            @Override
            public void onSwitched(LabeledSwitch labeledSwitch, boolean isOn) {
                // Implement your switching logic here

                if (isOn)
                {
                    showdatainarabic();
                } else {
                    showdatainenglish();
                }
            }

        });



        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent( Feedback_Menu.this , Value_Feedback.class);
                startActivity(intent);
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email_validation = customer_info.getText().toString();
                contact_validation = customer_info2.getText().toString();

                if (email_validation.equals("") && contact_validation.equals(""))
                {
                    SweetAlertDialog pDialog = new SweetAlertDialog(Feedback_Menu.this, SweetAlertDialog.ERROR_TYPE);
                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    pDialog.setTitleText("Please fill form properly..!!");
                    pDialog.setCancelable(true);
                    pDialog.show();
                    return;
                }

                else if (contact_validation.equals(""))
                {
                    SweetAlertDialog pDialog = new SweetAlertDialog(Feedback_Menu.this, SweetAlertDialog.ERROR_TYPE);
                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    pDialog.setTitleText("Contact Number Is Required..!!");
                    pDialog.setCancelable(true);
                    pDialog.show();
                    return;
                }

//                email_validation.matches(emailPattern) &&

                else if (email_validation.length() >0 && contact_validation.length() >0)
                {
                    customer_info.setText("");
                    customer_info2.setText("");


                    if (swToggle.isOn())
                    {
                        loadServeyinarabic();
                    }

                    else
                    {
                        loadServeyinenglish();

                    }

                }

                else
                {
//                    SweetAlertDialog pDialog = new SweetAlertDialog(Feedback_Menu.this, SweetAlertDialog.ERROR_TYPE);
//                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
//                    pDialog.setTitleText("Enter Correct Email Format..!!");
//                    pDialog.setCancelable(true);
//                    pDialog.show();
//                    return;
                }
            }
        });
    }

    private void loadServeyinenglish()
    {
        pdialog = Utilss.showSweetLoader(Feedback_Menu.this, SweetAlertDialog.PROGRESS_TYPE, "Fetching Data...");
        String makeUrl = "http://api.surveymenu.dwtdemo.com/api/en/feedback/"+MainActivity.value7+"/question";
        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.GET, makeUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("HttpClient", "success! response: " + response.toString());
                try {
                    JSONObject mRes = response.getJSONObject("data");
                    Intent i = new Intent(getApplicationContext(),Questions_Screen.class);


                    i.putExtra("mylist",  mRes.getString("questions"));
                    i.putExtra("FeedbackId", MainActivity.value7);
                    i.putExtra("token", MainActivity.value2);
                    i.putExtra("email", email_validation);
                    i.putExtra("phone", contact_validation);

                    i.putExtra("Toggle_State" , ""+swToggle.isOn());
                    Log.d("My_Toggle_State2" , ""+swToggle.isOn());



                    startActivity(i);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Utilss.hideSweetLoader(pdialog);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Utilss.hideSweetLoader(pdialog);
                    }
                });
            }
        }) {

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "Bearer "+MainActivity.value2);
                headers.put("Accept", "*/*");
                headers.put("Cache-Control", "no-cache");

                headers.put("Host", "api.surveymenu.dwtdemo.com");
                headers.put("Accept-Encoding", "gzip, deflate");
                headers.put("cache-control", "no-cache");

                return headers;
            }
        };

        Singleton.getInstance(this).getRequestQueue().add(sr);
    }






    private void loadServeyinarabic()
    {

        pdialog = Utilss.showSweetLoader(Feedback_Menu.this, SweetAlertDialog.PROGRESS_TYPE, "Fetching Data...");
        String makeUrl = "http://api.surveymenu.dwtdemo.com/api/ar/feedback/"+MainActivity.value7+"/question";
        JsonObjectRequest sr = new JsonObjectRequest(Request.Method.GET, makeUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("HttpClient", "success! response: " + response.toString());
                try {
                    JSONObject mRes = response.getJSONObject("data");
                    Intent i = new Intent(getApplicationContext(),Questions_Screen.class);


                    i.putExtra("mylist",  mRes.getString("questions"));
                    i.putExtra("FeedbackId", MainActivity.value7);
                    i.putExtra("token", MainActivity.value2);
                    i.putExtra("email", email_validation);
                    i.putExtra("phone", contact_validation);
//i.putExtra("Toggle_State" , ""+swToggle.isOn());
//Log.d("My_Toggle_State1" , ""+swToggle.isOn());


                    startActivity(i);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Utilss.hideSweetLoader(pdialog);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Utilss.hideSweetLoader(pdialog);
                    }
                });
            }
        }) {

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "Bearer "+MainActivity.value2);
                headers.put("Accept", "*/*");
                headers.put("Cache-Control", "no-cache");

                headers.put("Host", "api.surveymenu.dwtdemo.com");
                headers.put("Accept-Encoding", "gzip, deflate");
                headers.put("cache-control", "no-cache");

                return headers;
            }
        };

        Singleton.getInstance(this).getRequestQueue().add(sr);
    }

    private void showdatainenglish()
    {
        textView.setText("We value your feedback!");
        customer_info.setHint("Email Address");
        customer_info.setTextDirection(View.TEXT_DIRECTION_LTR);
//        customer_info.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        customer_info2.setHint("Contact Number");
        customer_info2.setTextDirection(View.TEXT_DIRECTION_LTR);

        //        customer_info2.setInputType(InputType.TYPE_CLASS_PHONE);
//        customer_info2.setTextDirection(View.TEXT_DIRECTION_FIRST_STRONG_LTR);
        home.setText("HOME");
        next.setText("NEXT");
    }

    private void showdatainarabic()
    {
        textView.setText("نحن نقدر ملاحظاتك");

        customer_info.setHint("البريد الإلكتروني");
        customer_info.setTextDirection(View.TEXT_DIRECTION_RTL);
//        customer_info.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        customer_info2.setHint("رقم التواصل");
        customer_info2.setTextDirection(View.TEXT_DIRECTION_RTL);

//        customer_info2.setTextDirection(View.TEXT_DIRECTION_ANY_RTL);
//        customer_info2.setInputType(InputType.TYPE_CLASS_PHONE);
        home.setText("الصفحة الرئيسية");
        next.setText("التالى");
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    public void onBackPressed() {
//        Intent intent = new Intent(Product_Detail.this, ProductsActivity.class);
//        finish();
//        startActivity(intent);
    }

    public void  loadServey()
    {

    }






}
