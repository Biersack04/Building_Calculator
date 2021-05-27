package com.example.buildingcalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buildingcalculator.roomDataBase.Project;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private ArrayList<RecyclerItem> listItems;
    private Context mContext;
    private final LayoutInflater inflater;
    interface OnStateClickListener{
        void onStateClick(RecyclerItem state, int position);
    }
    private final OnStateClickListener onClickListener;

    public RecyclerAdapter(ArrayList<RecyclerItem> listItems, Context mContext,OnStateClickListener onClickListener) {
        this.listItems = listItems;
        this.onClickListener = onClickListener;
        this.inflater = LayoutInflater.from(mContext);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        ViewHolder evh = new ViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final RecyclerItem itemList = listItems.get(position);
        holder.txtTitle.setText(itemList.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                onClickListener.onStateClick(itemList, position);
            }
        });

    }

    @Override
    public int getItemCount() {
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
        }
    }
}