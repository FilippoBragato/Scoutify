package com.filippobragato.reparto.RecViewAdapter;

import android.content.Intent;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.filippobragato.reparto.AddScoutActivity;
import com.filippobragato.reparto.CardActivity;
import com.filippobragato.reparto.MainActivity;
import com.filippobragato.reparto.R;
import com.filippobragato.reparto.backend.Scout;
import com.google.android.material.card.MaterialCardView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ScoutRecViewAdapter extends RecyclerView.Adapter<ScoutRecViewAdapter.ViewHolder> {

    private ArrayList<Scout> scouts;
    private MainActivity activity;

    public ScoutRecViewAdapter(ArrayList<Scout> scouts, MainActivity activity) {
        this.scouts = scouts;
        this.activity = activity;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scout_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ScoutRecViewAdapter.ViewHolder holder, int position) {
        holder.scoutName.setText(scouts.get(position).getName());
        holder.scoutRole.setText(scouts.get(position).getRole());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activity.isCardFlagOn()) activity.toggleSelectedModeOff();
                Intent intent = new Intent(v.getContext(), CardActivity.class);
                intent.putExtra("scout_ID", scouts.get(holder.getAdapterPosition()).getId());
                v.getContext().startActivity(intent);
            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (activity.isCardFlagOn()) {
                    activity.toggleSelectedModeOff();
                    activity.invalidateOptionsMenu();
                }
                else {
                    holder.cardView.setChecked(true);
                    activity.toggleSelectedModeOn(holder.cardView, scouts.get(holder.getAdapterPosition()));
                }
                return true;
            }
        });
        if(scouts.get(position).getImageUri()!=null){
            Glide.with(activity).load(scouts.get(position).getImageUri()).into(holder.scoutImage);
            holder.scoutImage.setBackgroundColor(activity.getResources().getColor(R.color.white_50));
        }
    }

    @Override
    public int getItemCount() {
        return scouts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView scoutName, scoutRole;
        private ImageView scoutImage;
        private MaterialCardView cardView;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            scoutName = itemView.findViewById(R.id.scoutNameClickable);
            scoutRole = itemView.findViewById(R.id.scoutNameRole);
            cardView = itemView.findViewById(R.id.scoutNameCard);
            scoutImage = itemView.findViewById(R.id.scoutImageInCard);
        }
    }
}
