package com.example.ships_version2.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ships_version2.R;
import com.example.ships_version2.structures.state;

import java.util.List;

public class StateAdapter  extends RecyclerView.Adapter<StateAdapter.ViewHolder>{
    private final LayoutInflater inflater;
    private final List<state> states;
    public StateAdapter(Context context, List<state> states) {
        this.states = states;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public StateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.hp, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(StateAdapter.ViewHolder holder, int position) {
        state state = states.get(position);
        holder.flagView.setBackgroundResource(R.drawable.img_1);
    }
    @Override
    public int getItemCount() {
        return states.size();
    }
    public void update(List<state> datas){
        datas.clear();
        datas.addAll(datas);
        notifyDataSetChanged();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        final View flagView;
        ViewHolder(View view){
            super(view);
            flagView = view.findViewById(R.id.hp_point);
        }
    }
}