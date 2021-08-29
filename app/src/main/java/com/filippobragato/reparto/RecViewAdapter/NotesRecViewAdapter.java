package com.filippobragato.reparto.RecViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.filippobragato.reparto.R;
import com.filippobragato.reparto.backend.Note;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class NotesRecViewAdapter extends RecyclerView.Adapter<NotesRecViewAdapter.ViewHolder>{

    private List<Note> notes;

    public NotesRecViewAdapter(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull NotesRecViewAdapter.ViewHolder holder, int position) {
        holder.content.setText(notes.get(position).getText());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("kk:mm, dd MMM yyyy", Locale.ITALY);
        holder.date.setText(simpleDateFormat.format(notes.get(position).getDate()));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView date, content;
        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.noteData);
            content = itemView.findViewById(R.id.noteContent);
        }
    }
}
