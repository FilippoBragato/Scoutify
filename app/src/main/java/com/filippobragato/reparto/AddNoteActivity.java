package com.filippobragato.reparto;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.filippobragato.reparto.backend.Note;
import com.filippobragato.reparto.database.RoomDB;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;
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

        // find element
        confirmButton = findViewById(R.id.addNoteButtonConfirm);
        textNote = findViewById(R.id.newNoteContainer);

        //Add on click
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = Objects.requireNonNull(textNote.getEditText()).getText().toString();
                if(!txt.equals("")) {
                    Note note = new Note(txt);
                    Map<String, Note> map = new HashMap<>();
                    String name = String.format("note_%03d", getIntent().getIntExtra("note", -1));
                    map.put(name, note);
                    FirebaseFirestore.getInstance().collection("lissaro").document("summary/scout/"+getIntent().getIntExtra("id", -1)+"/extra/note")
                            .set(map, SetOptions.merge());
                }
                finish();
            }
        });
    }
}
