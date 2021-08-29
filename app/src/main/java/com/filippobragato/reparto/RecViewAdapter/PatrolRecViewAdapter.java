package com.filippobragato.reparto.RecViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.filippobragato.reparto.MainActivity;
import com.filippobragato.reparto.R;
import com.filippobragato.reparto.backend.Patrol;
import com.filippobragato.reparto.backend.Scout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PatrolRecViewAdapter extends RecyclerView.Adapter<PatrolRecViewAdapter.ViewHolder> {

    private ArrayList<Patrol> patrols;
    private MainActivity activity;

    public PatrolRecViewAdapter(ArrayList<Patrol> patrols, MainActivity activity) {
        this.patrols = patrols;
        this.activity = activity;
        notifyDataSetChanged();
    }

    public void setPatrols(ArrayList<Patrol> patrols) {
        this.patrols = patrols;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.patrol_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PatrolRecViewAdapter.ViewHolder holder, int position) {
        //holder.patrolName.setText(patrols.get(position).getName());
        ScoutRecViewAdapter adapter = new ScoutRecViewAdapter(patrols.get(position).getPatrollers(), activity);
        holder.scoutRecyclerView.setAdapter(adapter);
        holder.scoutRecyclerView.setLayoutManager(new GridLayoutManager(holder.itemView.getContext(), 2));
        holder.scoutRecyclerView.setNestedScrollingEnabled(false);
    }


    @Override
    public int getItemCount() {
        return patrols.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView patrolName;
        private RecyclerView scoutRecyclerView;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            //patrolName = itemView.findViewById(R.id.patrolName);
            scoutRecyclerView = itemView.findViewById(R.id.scoutRecyclerView);
        }
    }
}
