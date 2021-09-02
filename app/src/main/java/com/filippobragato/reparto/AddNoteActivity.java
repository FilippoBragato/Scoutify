package com.filippobragato.reparto;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.filippobragato.reparto.backend.Note;
import com.filippobragato.reparto.database.NoteDao;
import com.filippobragato.reparto.database.RoomDB;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class AddNoteActivity extends AppCompatActivity {
    private Button confirmButton;
    private TextInputLayout textNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        //get database
        RoomDB database = RoomDB.getInstance(this);
        NoteDao noteDao = database.noteDao();

        // find element
        confirmButton = findViewById(R.id.addNoteButtonConfirm);
        textNote = findViewById(R.id.newNoteContainer);

        //Add on click
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = Objects.requireNonNull(textNote.getEditText()).getText().toString();
                if(!txt.equals("")) {
                    Note note = new Note(txt, getIntent().getIntExtra("scout_ID", -1));
                    noteDao.insert(note);
                }
                finish();
            }
        });
    }
}
