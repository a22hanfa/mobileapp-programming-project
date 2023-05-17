package com.example.mobileapp_programming_projekt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<inputDecoder> items;
    private LayoutInflater layoutInflater;
    private OnClickListener onClickListener;

    public RecyclerViewAdapter(Context context, ArrayList<inputDecoder> items, OnClickListener onClickListener) {
        this.layoutInflater = LayoutInflater.from(context);
        this.items = items;
        this.onClickListener = onClickListener;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        inputDecoder item = items.get(position);
        holder.textViewName.setText(item.getName());
        holder.textViewLocation.setText(item.getLocation());
        holder.textViewCategory.setText(item.getCategory());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewName, textViewLocation, textViewCategory;

        ViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewLocation = itemView.findViewById(R.id.text_view_location);
            textViewCategory = itemView.findViewById(R.id.text_view_category);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onClickListener.onClick(items.get(getAdapterPosition()));
        }
    }

    public interface OnClickListener {
        void onClick(inputDecoder item);
    }

    public void setItems(ArrayList<inputDecoder> items) {
        this.items = items;
    }
}