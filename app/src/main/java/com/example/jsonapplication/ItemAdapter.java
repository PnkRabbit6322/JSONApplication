package com.example.jsonapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.Serializable;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> implements Serializable {

    Context context;
    RecyclerView rcv;

    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int itemPosition = rcv.getChildLayoutPosition(view);
            ItemModel item = items.get(itemPosition);
            final Intent[] intent = new Intent[1];
            intent[0] = new Intent(context, JSONDisplay.class);
            intent[0].putExtra("item", item);
            context.startActivities(intent);
        }
    };

    List<ItemModel> items;

    public ItemAdapter(List<ItemModel> items, RecyclerView rcv) {
        this.items = items;
        this.rcv = rcv;
    }

    @NonNull
    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        itemView.setOnClickListener(mOnClickListener);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemViewHolder holder, int position) {
        ItemModel item = items.get(position);
        holder.textName.setText(item.getName());
        holder.textEmail.setText(item.getEmail());
        Glide.with(holder.itemView).load("https://lebavui.github.io" + item.getThumbnailSource()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.getImage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements Serializable {
        TextView textName;
        TextView textEmail;
        ImageView thumbnail;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();

            textName = itemView.findViewById(R.id.detail_name);
            textEmail = itemView.findViewById(R.id.text_email);
            thumbnail = itemView.findViewById(R.id.detail_photo);

        }
        public ImageView getImage(){ return this.thumbnail;}
    }
}
