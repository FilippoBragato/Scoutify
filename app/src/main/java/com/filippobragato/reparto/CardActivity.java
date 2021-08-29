package com.filippobragato.reparto;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.filippobragato.reparto.RecViewAdapter.NotesRecViewAdapter;
import com.filippobragato.reparto.backend.Progression;
import com.filippobragato.reparto.backend.Scout;
import com.filippobragato.reparto.database.NoteDao;
import com.filippobragato.reparto.database.RoomDB;
import com.filippobragato.reparto.database.ScoutDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class CardActivity extends AppCompatActivity {

    private Scout scout;
    private RoomDB database;
    private RecyclerView notesRecView;
    private NotesRecViewAdapter adapter;
    private TextView name, role, patrol, summary;
    private ImageView imageView;
    private FloatingActionButton add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);


        //Initialize data from Database
        database = RoomDB.getInstance(this);
        ScoutDao scoutDao = database.scoutDao();
        NoteDao noteDao = database.noteDao();

        scout = scoutDao.findByScoutId(getIntent().getIntExtra("scout_ID", -1));
        scout.setNotes(noteDao.getNotesOf(scout.getId()));


        //View Data
        //Init all element
        notesRecView = findViewById(R.id.scoutNotes);
        name = findViewById(R.id.scoutName);
        role = findViewById(R.id.scoutRole);
        patrol = findViewById(R.id.scoutPatrol);
        summary = findViewById(R.id.scoutSummary);
        add = findViewById(R.id.addNoteButton);
        imageView = findViewById(R.id.scoutImage);

        //TODO: Add images and summary

        //Set element
        name.setText(scout.getName());
        role.setText(scout.getRole());
        patrol.setText(scout.getPatrol());
        if(scout.getImageUri()!=null)
            Glide.with(this).load(scout.getImageUri()).into(imageView);

        //Set RecycleView
        adapter = new NotesRecViewAdapter(scout.getNotes());
        notesRecView.setAdapter(adapter);
        notesRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        notesRecView.addItemDecoration(new DividerItemDecoration(notesRecView.getContext(), DividerItemDecoration.VERTICAL));


        //Set on Click methods
        summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CardActivity.this, ProgressionActivity.class).putExtra("scout_ID", scout.getId()));
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CardActivity.this, AddNoteActivity.class)
                        .putExtra("scout_ID", scout.getId()));
            }
        });
    }

    @Override
    protected void onRestart() {
        NoteDao noteDao = database.noteDao();
        scout.setNotes(noteDao.getNotesOf(scout.getId()));
        adapter.setNotes(scout.getNotes());
        super.onRestart();
    }
}