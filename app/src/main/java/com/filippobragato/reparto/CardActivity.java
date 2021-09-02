package com.filippobragato.reparto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.filippobragato.reparto.RecViewAdapter.NotesRecViewAdapter;
import com.filippobragato.reparto.backend.Score;
import com.filippobragato.reparto.backend.Scout;
import com.filippobragato.reparto.database.NoteDao;
import com.filippobragato.reparto.database.RoomDB;
import com.filippobragato.reparto.database.ScoutDao;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
import java.util.Locale;

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

        for (int i = 0; i < scores.length; i++) {
            initializeScore(i);
        }

        //TODO: Add summary

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
        notesRecView.setNestedScrollingEnabled(false);


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
                                database.scoreDao().insert(new Score(0, scout.getId(), field ,mark));
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
        List<Score> marks = database.scoreDao().getScores(scout.getId(), field);
        if (marks.size()==0) {
            ((ImageView)scores[field].findViewById(R.id.cardScoreImage)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_constant));
            ((TextView)scores[field].findViewById(R.id.cardScoreNumber)).setText("?");
            return;
        }
        double mean = 0;
        for (int j = 0; j < marks.size(); j++) {
            mean += marks.get(j).getScore();
        }
        mean = mean / marks.size();
        double last = marks.get(marks.size()-1).getScore();
        if(Math.abs(last-mean) < 0.1){
            ((ImageView)scores[field].findViewById(R.id.cardScoreImage)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_constant));
        }
        else {
            if (Math.abs(last-mean) < 1) {
                if (last - mean > 0)
                    ((ImageView) scores[field].findViewById(R.id.cardScoreImage)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_medium_up));
                else
                    ((ImageView) scores[field].findViewById(R.id.cardScoreImage)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_medium_down));
            }
            else {
                if (last - mean > 0)
                    ((ImageView) scores[field].findViewById(R.id.cardScoreImage)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_strong_up));
                else
                    ((ImageView) scores[field].findViewById(R.id.cardScoreImage)).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_strong_down));
            }
        }
        ((TextView)scores[field].findViewById(R.id.cardScoreNumber)).setText(String.format(Locale.ITALY, "%.1f", mean));
    }

    @Override
    protected void onRestart() {
        NoteDao noteDao = database.noteDao();
        scout.setNotes(noteDao.getNotesOf(scout.getId()));
        adapter.setNotes(scout.getNotes());
        super.onRestart();
    }
}