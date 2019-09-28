package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.FormBody;
import okhttp3.RequestBody;

public class Questions_Screen extends AppCompatActivity implements View.OnClickListener {

    TextView question;
    EditText mComments;
    int count = 0;
    MyData[] myData;
    String emails_intent;
    Button imageView2;
    private ImageView rate1, rate2, rate3, rate4, rate5, sendBtn;

    private ImageView imageView22, imageView23, imageView24,imageView26;

    int[] ratings;
    int contact_intent;
    Boolean bool_value;
    String data, fbid, token, comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions__screen);

        data = getIntent().getStringExtra("mylist");



//                bool_value = getIntent().getExtras().getBoolean("Toggle_State");
//                Log.e("Bool_Value", ""+bool_value);

        emails_intent = getIntent().getStringExtra("email");
        contact_intent = getIntent().getIntExtra("phone" , 0);



        Log.e("My Json String", data);

        Gson gson = new Gson();
        myData = gson.fromJson(data, MyData[].class);
        ratings = new int[myData.length + 1];

        question = (TextView) findViewById(R.id.textview);
        question.setText(myData[count].getQuestion());



        imageView22 = (ImageView) findViewById(R.id.imageView22);
        imageView23 = (ImageView) findViewById(R.id.imageView23);
        imageView24 = (ImageView) findViewById(R.id.imageView24);
        imageView26 = (ImageView) findViewById(R.id.imageView26);

        rate1 = (ImageView) findViewById(R.id.imageView1);
        rate2 = (ImageView) findViewById(R.id.imageView25);
        rate3 = (ImageView) findViewById(R.id.imageView3);
        rate4 = (ImageView) findViewById(R.id.imageView4);

        imageView2 = findViewById(R.id.home_btn);


        if (Feedback_Menu.swToggle.isOn())
        {
            imageView2.setText("الصفحة الرئيسية");

            imageView22.setImageResource(R.mipmap.very_gud_arabic);
            imageView23.setImageResource(R.mipmap.gud_arabic);
            imageView24.setImageResource(R.mipmap.fair_arabic);
            imageView26.setImageResource(R.mipmap.poor_arabic);

        }

        else
        {
            imageView2.setText("HOME");

            imageView22.setImageResource(R.mipmap.very_gud);
            imageView23.setImageResource(R.mipmap.gud);
            imageView24.setImageResource(R.mipmap.fair_btn);
            imageView26.setImageResource(R.mipmap.poor_btn);
        }


        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(Questions_Screen.this , Value_Feedback.class);
                startActivity(intent);
                finish();
            }
        });


//        sendBtn = (Button) findViewById(R.id.send_comment);
//
//        mComments = findViewById(R.id.my_comments);

        rate1.setOnClickListener(this);
        rate2.setOnClickListener(this);
        rate3.setOnClickListener(this);
        rate4.setOnClickListener(this);
//        rate5.setOnClickListener(this);


    }

    public void loadQuestion(int c) {
        int Length = myData.length;
        count++;
        if (count < Length) {

            question.setText(myData[count].getQuestion());
        } else {

            Intent intent = new Intent(Questions_Screen.this, Feedback_Submit.class);
            intent.putExtra("Total_Things", myData.length);
            intent.putExtra("stock_list", data);
            intent.putExtra("Total_Things_Data", ratings);


            intent.putExtra("Email_Value", emails_intent);
            intent.putExtra("Contact_Value", contact_intent);

            intent.putExtra("Again_Bool_Value", bool_value);

            Log.d("Array", "" + ratings);
            startActivity(intent);


        }


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.imageView1:
                ratings[count] = 1;
                break;
            case R.id.imageView25:
                ratings[count] = 2;
                break;
            case R.id.imageView3:
                ratings[count] = 3;
                break;
            case R.id.imageView4:
                ratings[count] = 4;
                break;

        }
        loadQuestion(count);
    }


    public void onBackPressed() {
//        Intent intent = new Intent(Product_Detail.this, ProductsActivity.class);
//        finish();
//        startActivity(intent);
    }

//    public void submitResults(int[] ratings, String comments) {
//
//        JSONObject level1 = new JSONObject();         // Included object
//        try {
//
//            level1.put("feedback_id", MainActivity.value7);
//            level1.put("lang", "en");
//
//
//            JSONObject level3 = new JSONObject();
//            level3.put("email", "faizy3163@gmail.com");
//            level3.put("contact", "0321242842");
//            level1.put("customer", level3);
//
//
//            JSONArray myArray = new JSONArray();
//            for (int i = 0; i < myData.length; i++) {
//
//
//                JSONObject level2 = new JSONObject();
//                level2.put("question_id", myData[i].getId());
//                Log.e("Questiion_ID", myData[i].getId().toString());
//                level2.put("response_id", ratings[i]);
//                Log.e("Rating", String.valueOf(ratings[i]));
//                myArray.put(level2);
//            }
//
//
//            level1.put("Rating", myArray);
//            level1.put("comment", comments);
//
//
//        } catch (JSONException e) {
//            Log.e("Json Error", e.toString());
//        }
//
//        Log.e("MyResult Request", level1.toString());
//        String myUrl = "http://api.surveymenu.dwtdemo.com/api/feedback/";
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, myUrl, level1, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Toast.makeText(QuestionsActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
//                Log.e("Success", response.toString());
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(QuestionsActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//                Log.e("Error", error.toString());
//
//            }
//        }) {
//
//            @Override
//            public Map<String, String> getHeaders() {
//                Map<String, String> headers = new HashMap<>();
//                headers.put("Authorization", "Bearer " + MainActivity.value2);
//                Log.e("Token", MainActivity.value2);
//                //headers.put("Content-Type", "application/json");
//                headers.put("User-Agent", "PostmanRuntime/7.17.1");
//                headers.put("Accept", "*/*");
//                headers.put("Cache-Control", "no-cache");
//                headers.put("Postman-Token", "6d6adfd9-12e3-4860-8cc2-ff458425702e,a4278764-20f9-4756-9edc-f5f1ceb629ec");
//                headers.put("Host", "api.surveymenu.dwtdemo.com");
//                headers.put("Accept-Encoding", "gzip, deflate");
//                headers.put("Connection", "keep-alive");
//                headers.put("cache-control", "no-cache");
//
//
//                return headers;
//            }
//        };
//        Singleton.getInstance(this).getRequestQueue().add(jsonObjectRequest);
//
//
//    }
}

//public class Questions_Screen extends AppCompatActivity {
//
//    TextView textview;
//    SweetAlertDialog pdialog;
//    String s1;
//    int int_s1;
//
//    int valuess=0;
//    ImageView imageView1,imageView25,imageView3,imageView4;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_questions__screen);
//
//        textview=findViewById(R.id.textview);
//
//        imageView1=findViewById(R.id.imageView1);
//        imageView25=findViewById(R.id.imageView25);
//        imageView3=findViewById(R.id.imageView3);
//        imageView4=findViewById(R.id.imageView4);
//
//
//        imageView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                valuess=1;
//                Log.d("Valuess" , ""+valuess);
//
//            }
//        });
//
//
//
//        imageView25.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                valuess=2;
//                Log.d("Valuess" , ""+valuess);
//            }
//        });
//
//
//
//
//        imageView3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                valuess=3;
//                Log.d("Valuess" , ""+valuess);
//
//            }
//        });
//
//
//
//
//        imageView4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                valuess=4;
//                Log.d("Valuess" , ""+valuess);
//
//            }
//        });
//
//
//
//        getquestions();
//    }
////    value7
//
//    private void getquestions()
//    {
//        pdialog = Utilss.showSweetLoader(Questions_Screen.this, SweetAlertDialog.PROGRESS_TYPE, "Fetching Questions...");
//
//        RequestQueue queue = Volley.newRequestQueue(Questions_Screen.this);
//        String url = "http://api.surveymenu.dwtdemo.com/api/en/feedback/"+MainActivity.value7+"/question";
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Utilss.hideSweetLoader(pdialog);
//                            }
//                        });
//
//                        Log.d("Json-response1", response);
//
//                        try {
//                            JSONObject respone = new JSONObject(response);
//                            JSONObject respones = respone.getJSONObject("data");
//                            JSONArray jsonArray = respones.getJSONArray("questions");
////
//
////                            JSONObject jsonObject = jsonArray.optJSONObject();
////                            String s1 = jsonObject.getString("question");
////                            textview.setText(s1);
////                            Log.d("Quesionsss" , s1);
//
//
//
//                          for (int i = 0; i < jsonArray.length(); i++)
//                            {
////                                JSONObject jsonObject = jsonArray.optJSONObject(String.valueOf(jsonArray.get(i)));
//
//                            }
//
//                            JSONObject jsonObject = jsonArray.optJSONObject(0);
//                             s1 = jsonObject.getString("question");
//                             int_s1 = jsonObject.getInt("id");
//
//                            textview.setText(s1);
//                            Log.d("Quesionsss" , s1  +   "    " + int_s1);
//
////
////                            for (int i = 0; i < jsonArray.length(); i++)
////                            {
//////                                JSONObject jsonObject = jsonArray.optJSONObject(String.valueOf(jsonArray.get(i)));
////
////                            }
//
////                            {
////                                String s1 = jsonObject.getString("question");
////                                Log.d("Quesionsss" , s1);
////                            }
//
////                            //
//
//
//
//                        }
//
//                        catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//
//
//
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(final VolleyError error) {
//
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Utilss.hideSweetLoader(pdialog);
//
//                        Log.d("Failure" , error.toString());
//
//                    }
//                });
//            }
//        }){
//            @Override
//            public Map getHeaders() {
//                HashMap headers = new HashMap();
//                headers.put("Authorization", "Bearer "+MainActivity.value2);
//                return headers;
//            }
//        };
//        queue.add(stringRequest);







//    }
//}
