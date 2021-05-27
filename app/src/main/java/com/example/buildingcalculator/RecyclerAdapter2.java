package com.example.buildingcalculator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

import static com.example.buildingcalculator.Constants.ACTIVITY_SELECTION;
import static com.example.buildingcalculator.Constants.WORKS;

public class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerAdapter2.ViewHolder> {
    private ArrayList<RecyclerItem> listItems;
    public RecyclerItem item;
    private Context mContext;
    private OnStateClickListener onClickListener;

    public RecyclerAdapter2(ArrayList<RecyclerItem> listItems, Context mContext, OnStateClickListener onClickListener) {
        this.listItems = listItems;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
    }


    interface OnStateClickListener{
        void onStateClick(RecyclerItem listItems, int position);
    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        ViewHolder evh = new ViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        RecyclerItem itemList = listItems.get(position);
        holder.txtTitle.setText(itemList.getTitle());

        // обработка нажатия
        holder.itemView.setOnClickListener(v -> {
            // вызываем метод слушателя, передавая ему данные
            onClickListener.onStateClick(itemList, position);
        });

    }


    @Override
    public int getItemCount() {
        if (listItems == null) {
            return 0;
        }
        return listItems.size();
    }

    public void filterList(ArrayList<RecyclerItem> filteredList) {
        listItems = filteredList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{


        public TextView txtTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title_recycler);
            itemView.setOnClickListener(v -> {

            });
        }
    }
}
