package com.filippobragato.reparto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.filippobragato.reparto.RecViewAdapter.NotesRecViewAdapter;
import com.filippobragato.reparto.backend.Note;
import com.filippobragato.reparto.backend.Scout;
import com.filippobragato.reparto.database.RoomDB;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class CardActivity extends AppCompatActivity {

    private Scout scout;
    private RoomDB database;
    private RecyclerView notesRecView;
    private NotesRecViewAdapter adapter;
    private TextView name, role, patrol, summary;
    private ImageView imageView;
    private FloatingActionButton add;
    private CardView[] scores;
    private String[] scoresField;
    private FirebaseFirestore db;
    private DocumentReference doc;
    private List<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);


        //Initialize data from Database
        db = FirebaseFirestore.getInstance();
        int id = getIntent().getIntExtra("scout_ID", -1);


        //View Data
        //Init all element
        notesRecView = findViewById(R.id.scoutNotes);
        name = findViewById(R.id.scoutName);
        role = findViewById(R.id.scoutRole);
        patrol = findViewById(R.id.scoutPatrol);
        summary = findViewById(R.id.scoutSummary);
        add = findViewById(R.id.addNoteButton);
        imageView = findViewById(R.id.scoutImage);
        scoresField = getResources().getStringArray(R.array.scoreField);

        scores = new CardView[18];
        scores[0] = findViewById(R.id.scoutCharacterLeaders);
        scores[1] = findViewById(R.id.scoutCharacterScout);
        scores[2] = findViewById(R.id.scoutCharacterRole);
        scores[3] = findViewById(R.id.scoutCharacterSelf);
        scores[4] = findViewById(R.id.scoutSkillStoker);
        scores[5] = findViewById(R.id.scoutSkillPioneering);
        scores[6] = findViewById(R.id.scoutSkillCooker);
        scores[7] = findViewById(R.id.scoutSkillOrienteering);
        scores[8] = findViewById(R.id.scoutSkillTopography);
        scores[9] = findViewById(R.id.scoutSkillMeteorology);
        scores[10] = findViewById(R.id.scoutSkillSignaler);
        scores[11] = findViewById(R.id.scoutSkillFirstAid);
        scores[12] = findViewById(R.id.scoutSkillArtist);
        scores[13] = findViewById(R.id.scoutSkillExpressionism);
        scores[14] = findViewById(R.id.scoutSkillCampism);
        scores[15] = findViewById(R.id.scoutSkillNaturalism);
        scores[16] = findViewById(R.id.scoutSpirit);
        scores[17] = findViewById(R.id.scoutHealth);

        doc = db.collection("lissaro").document("summary").collection("scout").document(Integer.toString(id));
        doc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        scout = document.toObject(Scout.class);
                        Scout roomScout = RoomDB.getInstance(CardActivity.this).scoutDao().findByScoutId(scout.getId());
                        scout.setImageUri(roomScout.getImageUri());
                        for (int i = 0; i < scores.length; i++) {
                            initializeScore(i);
                        }
                        //Set element
                        name.setText(scout.getName());
                        role.setText(scout.getRole());
                        patrol.setText(scout.getPatrol());
                        if(scout.getImageUri()!=null)
                            Glide.with(CardActivity.this).load(scout.getImageUri()).into(imageView);
                        String s = "";
                        switch (scout.getVertical()) {
                            case 0:
                                s+="Piede tenero";
                                break;
                            case 1:
                                s+="Scout promessato";
                                break;
                            case 2:
                                s+="Scout di seconda classe";
                                break;
                            case 3:
                                s+="Scout di prima classe";
                                break;
                        }
                        if(!scout.getSpecialities().isEmpty()) {
                            if(scout.getSpecialities().size()==1){
                                s+= " con la specialità di ";
                                s= s+ scout.getSpecialities().get(0);
                            }
                            else {
                                s+= " con le specialità di ";
                                for (int i = 0; i < scout.getSpecialities().size()-2; i++) {
                                    s= s + scout.getSpecialities().get(i);
                                    s+=", ";
                                }
                                s = s+ scout.getSpecialities().get(scout.getSpecialities().size()-2);
                                s+= " e ";
                                s = s+ scout.getSpecialities().get(scout.getSpecialities().size()-1);
                            }
                        }
                        summary.setText(s);
                    }
                }
            }
        });
        notes = new ArrayList<>();
        adapter = new NotesRecViewAdapter(notes);
        notesRecView.setAdapter(adapter);
        notesRecView.setLayoutManager(new LinearLayoutManager(CardActivity.this, LinearLayoutManager.VERTICAL, true));
        notesRecView.addItemDecoration(new DividerItemDecoration(notesRecView.getContext(), DividerItemDecoration.VERTICAL));
        notesRecView.setNestedScrollingEnabled(false);
        updateNote();


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
                        .putExtra("id", scout.getId()).putExtra("note", adapter.getItemCount()));
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        updateNote();
    }

    private void updateNote() {
        DocumentReference document = doc.collection("extra").document("note");
        document.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if(task.isSuccessful()) {
                    DocumentSnapshot snap = task.getResult();
                    if(snap.exists()){
                        notes = new ArrayList<>();
                        Map<String, Object> notesMap = snap.getData();
                        Set<String> keys = notesMap.keySet();
                        List<String> listKey = new ArrayList<>(keys);
                        Collections.sort(listKey);
                        for (String key: listKey) {
                            Map<String, Object> n = (Map<String, Object>) notesMap.get(key);
                            notes.add(new Note((String) n.get("text"),((Timestamp) n.get("date")).toDate()));
                        }
                        adapter.setNotes(notes);
                    }
                }
            }
        });
    }

    private void initializeScore(int field) {
        ((TextView)scores[field].findViewById(R.id.cardScoreFieldText)).setText(scoresField[field]);
        scores[field].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View number = getLayoutInflater().inflate(R.layout.insert_number_dialog, null,false);
                AlertDialog dialog = new AlertDialog.Builder(CardActivity.this)
                        .setTitle(getString(R.string.dialogScore) + " " +scoresField[field])
                        .setView(number)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                double mark = Double.parseDouble(((TextInputEditText)number.findViewById(R.id.addScoreNoteScore)).getText().toString());
                                int difference = 0;
                                if(Math.abs(mark-scout.getScore().meanAsArray()[field])>0.1){
                                    if(mark>scout.getScore().meanAsArray()[field]){
                                        difference = 1;
                                    }
                                    else{
                                        difference = -1;
                                    }
                                    if(Math.abs(mark-scout.getScore().meanAsArray()[field])>0.5){
                                        if(mark>scout.getScore().meanAsArray()[field]){
                                            difference = 2;
                                        }
                                        else{
                                            difference = -2;
                                        }
                                    }
                                }
                                int number = scout.getScore().numAsArray()[field]+1;
                                if(number == 1)
                                    difference = 0;
                                double mean = (scout.getScore().meanAsArray()[field]*scout.getScore().numAsArray()[field]+ mark)/number;
                                int[] numbers = scout.getScore().numAsArray();
                                numbers[field]= number;
                                int[] differences = scout.getScore().diffAsArray();
                                differences[field] = difference;
                                double[] means = scout.getScore().meanAsArray();
                                means[field] = mean;
                                scout.getScore().applyMeanAsArray(means);
                                scout.getScore().applyDiffAsArray(differences);
                                scout.getScore().applyNumAsArray(numbers);
                                doc.update("score", scout.getScore());
                                updateScore(field);
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .create();
                dialog.show();
            }
        });
        updateScore(field);
    }

    private void updateScore(int field) {
        if (scout.getScore().numAsArray()[field]==0) {
            ((ImageView)scores[field].findViewById(R.id.cardScoreImage)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_constant));
            ((TextView)scores[field].findViewById(R.id.cardScoreNumber)).setText("?");
            return;
        }
        switch (scout.getScore().diffAsArray()[field]){
            case 0:
                ((ImageView)scores[field].findViewById(R.id.cardScoreImage)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_constant));
                break;
            case 1:
                ((ImageView) scores[field].findViewById(R.id.cardScoreImage)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_medium_up));
                break;
            case 2:
                ((ImageView) scores[field].findViewById(R.id.cardScoreImage)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_strong_up));
                break;
            case -1:
                ((ImageView) scores[field].findViewById(R.id.cardScoreImage)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_medium_down));
                break;
            case -2:
                ((ImageView) scores[field].findViewById(R.id.cardScoreImage)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_strong_down));
                break;
        }
        ((TextView)scores[field].findViewById(R.id.cardScoreNumber)).setText(String.format(Locale.ITALY, "%.1f", scout.getScore().meanAsArray()[field]));
    }
}