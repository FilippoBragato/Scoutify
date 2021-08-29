package com.filippobragato.reparto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.filippobragato.reparto.backend.Note;
import com.filippobragato.reparto.database.NoteDao;
import com.filippobragato.reparto.database.RoomDB;

import java.util.Date;

public class AddNoteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        //get database
        RoomDB database = RoomDB.getInstance(this);
        NoteDao noteDao = database.noteDao();

        // find element
        Button add = findViewById(R.id.saveNote);
        EditText text = findViewById(R.id.newNote);

        //Add on click
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = text.getText().toString();
                if(!txt.equals("")) {
                    Note note = new Note(txt, getIntent().getIntExtra("scout_ID", -1));
                    noteDao.insert(note);
                }
                finish();
            }
        });
    }

}
