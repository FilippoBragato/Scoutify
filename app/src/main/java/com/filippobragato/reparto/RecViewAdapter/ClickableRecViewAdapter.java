package com.filippobragato.reparto.RecViewAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.filippobragato.reparto.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ClickableRecViewAdapter extends RecyclerView.Adapter<ClickableRecViewAdapter.ViewHolder> {

    private ArrayList<String> specialities = new ArrayList<>();
    private OnElementListener onElementListener;

    public ClickableRecViewAdapter(ArrayList<String> specialities, OnElementListener onElementListener) {
        this.specialities = specialities;
        this.onElementListener = onElementListener;
        notifyDataSetChanged();
    }

    public void setSpecialities(ArrayList<String> specialities) {
        this.specialities = specialities;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clickable_list_item, parent, false);
        return new ViewHolder(view, onElementListener);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ClickableRecViewAdapter.ViewHolder holder, int position) {
        holder.clickableName.setText(specialities.get(position));
    }

    @Override
    public int getItemCount() {
        return specialities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView clickableName;
        private OnElementListener onElementListener;

        @Override
        public void onClick(View v) {
            onElementListener.onElementClick(getAdapterPosition());
        }

        private RelativeLayout rl;
        public ViewHolder(@NonNull @NotNull View itemView, OnElementListener onElementListener) {
            super(itemView);
            clickableName = itemView.findViewById(R.id.clickableSpeciality);
            this.onElementListener = onElementListener;
            itemView.setOnClickListener(this);
        }
    }

    public interface OnElementListener {
        void onElementClick(int position);
    }
}
