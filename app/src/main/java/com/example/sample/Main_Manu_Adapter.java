package com.example.sample;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sample.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by abdalla on 1/12/18.
 */

public class Main_Manu_Adapter extends RecyclerView.Adapter<FlowerViewHolder> {

    private Context mContext;
    private List<MainCategories_Pojo> mFlowerList;
    SweetAlertDialog progressbar;
    static int ji;

    Main_Manu_Adapter(Context mContext, List<MainCategories_Pojo> mFlowerList) {
        this.mContext = mContext;
        this.mFlowerList = mFlowerList;
    }

    @Override
    public FlowerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row_item, parent, false);
        return new FlowerViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final FlowerViewHolder holder, final int position) {
//        holder.mImage.setImageResource(R.drawable.carnation);

//        progressbar = Utilss.showSweetLoader(mContext, SweetAlertDialog.PROGRESS_TYPE, "Fetching Data...");

        holder.mTitle.setText(mFlowerList.get(position).getCategory());
//        holder.mImage.setImageResource(R.drawable.item2);
        Log.d("Image34" , ""+mFlowerList.get(position).getCategory());
        Log.d("IDiS" , ""+mFlowerList.get(position).getID());

        Picasso.with(mContext).load(mFlowerList.get(position).getImage()).into(holder.mImage);


        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(mContext , ProductsActivity.class);

                ji = mFlowerList.get(position).getID();
                boolean l =Main_MenuScreen.swToggle.isOn();
                Log.d("ValueOfBool" , ""+l);

                intent.putExtra("Toggle_Value" , l);
                intent.putExtra("Products_Id" , mFlowerList.get(position).getID());
                mContext.startActivity(intent);


//                ((Activity) mContext).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFlowerList.size();
    }


    public void setDataList(ArrayList<MainCategories_Pojo> contactList) {

        this.mFlowerList = contactList;
    }
}

class FlowerViewHolder extends RecyclerView.ViewHolder {

    ImageView mImage;
    TextView mTitle;
    CardView mCardView;

    FlowerViewHolder(View itemView) {
        super(itemView);

        mImage = itemView.findViewById(R.id.breakfast);
        mTitle = itemView.findViewById(R.id.text_my);
        mCardView = itemView.findViewById(R.id.cardclicked);


    }
}
