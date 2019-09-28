package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ThankuActivity extends AppCompatActivity {

    boolean Bools;
    TextView textView1234;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanku);



//        Bools = getIntent().getExtras().getBoolean("Bools");
//        Log.e("Bool_Value3", ""+Bools);


        textView1234 = findViewById(R.id.textView1234);


        if (Feedback_Menu.swToggle.isOn())
        {
        textView1234.setText("شكراً لك على ملاحظاتك القيمة، قم بزيارتنا مرة أخرى");
        }

        else
        {

            textView1234.setText("Thank you for your valuable feedback. Visit us again..");
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final Intent mainIntent = new Intent(ThankuActivity.this, Value_Feedback.class);
                startActivity(mainIntent);
                finish();
            }
        }, 5000);


    }

    public void onBackPressed() {
//        Intent intent = new Intent(Product_Detail.this, ProductsActivity.class);
//        finish();
//        startActivity(intent);
    }
}
