package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;
import in.goodiebag.carouselpicker.CarouselPicker;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    CarouselPicker crousalPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        crousalPicker = findViewById(R.id.crousalPicker);

        List<CarouselPicker.PickerItem> itemsImages = new ArrayList<>();
            itemsImages.add(new CarouselPicker.DrawableItem(R.drawable.download));
        itemsImages.add(new CarouselPicker.DrawableItem(R.mipmap.company_logo));
        itemsImages.add(new CarouselPicker.DrawableItem(R.drawable.error_center_x));

            CarouselPicker.CarouselViewAdapter imageAdapter= new CarouselPicker.CarouselViewAdapter(this,itemsImages,0);
            crousalPicker.setAdapter(imageAdapter);
    }
}
