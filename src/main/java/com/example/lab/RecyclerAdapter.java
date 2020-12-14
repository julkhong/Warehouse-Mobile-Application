package com.example.fit2081lab1;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    ArrayList<Item> data = new ArrayList<Item>();
    public void setData(ArrayList<Item> data) {
        this.data = data;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false); //CardView inflated as RecyclerView list item
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cardName.setText(data.get(position).getName());
        holder.cardQuantity.setText(data.get(position).getQuantity());
        holder.cardCost.setText(data.get(position).getPrice());
        holder.cardDescription.setText(data.get(position).getDescription());
        holder.cardFrozen.setText(data.get(position).getFrozen());


    }
    @Override
    public int getItemCount() {
        return data.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView cardName;
        public TextView cardQuantity;
        public TextView cardCost;
        public TextView cardDescription;
        public TextView cardFrozen;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardName = itemView.findViewById(R.id.card_name_id);
            cardQuantity = itemView.findViewById(R.id.card_quantity_id);
            cardCost = itemView.findViewById(R.id.card_cost_id);
            cardDescription = itemView.findViewById(R.id.card_description_id);
            cardFrozen= itemView.findViewById(R.id.card_frozen_id);

        }
    }
}
