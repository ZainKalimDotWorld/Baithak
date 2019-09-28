package com.example.sample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class SnapRecyclerAdapter_Details extends RecyclerView.Adapter<SnapRecyclerAdapter_Details.ReyclerViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<Item> items;
    String ACTION_INTENT;
    String ACTION_INTENT2="custom-message2";


    public SnapRecyclerAdapter_Details(Context context, ArrayList<Item> items) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.items = items;
    }

    @Override
    public ReyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.item_recycler_view_products_details, parent, false);

        return new ReyclerViewHolder(item);
    }

    @Override
    public void onBindViewHolder(final ReyclerViewHolder holder, final int position) {

        final Item item = items.get(position);
        Picasso.with(context).load(item.getImage()).into(holder.image);
        holder.appName.setText(item.getProduct());



        holder.clicked_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent("custom-message");
                intent.putExtra("ValueId",items.get(position).getID());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
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
        CardView clicked_card;

        private ReyclerViewHolder(final View v) {
            super(v);

            image = (ImageView) v.findViewById(R.id.image);
            appName = (TextView) v.findViewById(R.id.app_name);
            clicked_card = (CardView) v.findViewById(R.id.clicked_card);

//            app_name2 = (TextView) v.findViewById(R.id.app_name2);

        }
    }


    protected BroadcastReceiver ActivityDataReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
//            if(ACTION_INTENT.equals(intent.getAction()))
//            {
//                String text = intent.getStringExtra("TEXT");
//            }
        }
    };



}
