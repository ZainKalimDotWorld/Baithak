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

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class SnapRecyclerAdapter extends RecyclerView.Adapter<SnapRecyclerAdapter.ReyclerViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<Item> items;

    public SnapRecyclerAdapter(Context context, ArrayList<Item> items) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.items = items;
    }

    @Override
    public ReyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.item_recycler_view_products, parent, false);

        return new ReyclerViewHolder(item);
    }

    @Override
    public void onBindViewHolder(final ReyclerViewHolder holder, int position) {

        final Item item = items.get(position);

        Picasso.with(context).load(item.getImage()).into(holder.image);

        holder.appName.setText(item.getProduct());
        Log.d("Item_Price", ""+item.getPrice());

//        String value= item.getPrice();
//        float d=Float.parseFloat(value);
//        Log.d("Item_Price22", ""+d);

        holder.app_name2.setText(String.format("AED : %.2f", item.getPrice()));


        holder.category_clicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(context , Product_Detail.class);
                boolean l2 =ProductsActivity.swToggle.isOn();
                Log.d("ValueOfBool2" , ""+l2);
                intent.putExtra("ValueBool2" ,l2);


                intent.putExtra("Category_Id" , item.getID());
                intent.putExtra("Category_name" , item.getCategory());
                context.startActivity(intent);

                ((Activity) context).finish();

            }
        });
    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setDataList(ArrayList<Item> contactList) {

        this.items = contactList;
    }

    class ReyclerViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView appName,app_name2;
        CardView category_clicked;

        private ReyclerViewHolder(final View v) {
            super(v);

            image = (ImageView) v.findViewById(R.id.image);
            appName = (TextView) v.findViewById(R.id.app_name);
            app_name2 = (TextView) v.findViewById(R.id.app_name2);

            category_clicked = (CardView) v.findViewById(R.id.category_clicked);


        }
    }
}
