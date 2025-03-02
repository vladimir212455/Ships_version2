package com.example.ships_version2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StateAdapter  extends RecyclerView.Adapter<StateAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<state> states;

    StateAdapter(Context context, List<state> states) {
        this.states = states;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public StateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.hp, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(StateAdapter.ViewHolder holder, int position) {
        state state = states.get(position);
        holder.flagView.setImageResource(R.id.hp_point);
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView flagView;
        @SuppressLint("WrongViewCast")
        ViewHolder(View view){
            super(view);
            flagView = view.findViewById(R.id.hp_point);
        }
    }
}