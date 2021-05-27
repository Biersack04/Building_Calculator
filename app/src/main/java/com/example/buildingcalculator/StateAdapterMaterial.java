package com.example.buildingcalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.buildingcalculator.roomDataBase.Material;
import com.example.buildingcalculator.roomDataBase.Project;

import java.util.ArrayList;
import java.util.List;

public class StateAdapterMaterial extends RecyclerView.Adapter<StateAdapterMaterial.ViewHolder>{

    interface OnStateClickListener{
        void onStateClick(Material state, int position);
    }

    private final OnStateClickListener onClickListener;

    private final LayoutInflater inflater;
    private List<Material> states;

    private ArrayList<Material> listItems;

    StateAdapterMaterial(Context context, List<Material> states, OnStateClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.states = states;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public StateAdapterMaterial.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StateAdapterMaterial.ViewHolder holder, int position) {
        Material state = states.get(position);
        holder.nameView.setText(state.getName());


        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                onClickListener.onStateClick(state, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public void setProjects(List<Material> projects){
        this.states = projects;
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView;
        ViewHolder(View view){
            super(view);
            nameView = (TextView) view.findViewById(R.id.name);
        }
    }
}