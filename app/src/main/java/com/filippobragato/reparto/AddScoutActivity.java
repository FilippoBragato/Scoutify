package com.filippobragato.reparto;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.filippobragato.reparto.backend.FirstClass;
import com.filippobragato.reparto.backend.Progression;
import com.filippobragato.reparto.backend.Promise;
import com.filippobragato.reparto.backend.Scout;
import com.filippobragato.reparto.backend.SecondClass;
import com.filippobragato.reparto.database.RoomDB;
import com.filippobragato.reparto.database.ScoutDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddScoutActivity extends AppCompatActivity {

    private EditText name, birthday;
    private Spinner patrol, role, verticalProgression;
    private FloatingActionButton confirm;
    private CardView image;
    private ImageView imageView;
    private String imageString = null;
    private Calendar calendar;
    private List<String> patrols;
    private FrameLayout frameLayout;
    private NewPatrolFragment newPatrolFragment;
    private boolean newPatrolFlag = false;
    private RoomDB database;

    private boolean editMode;
    private Scout editScout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_scout);
        initializeFields();

        ArrayAdapter<CharSequence> adapterRole = ArrayAdapter.createFromResource(this,
                R.array.roleInPatrol, android.R.layout.simple_spinner_item);
        adapterRole.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        role.setAdapter(adapterRole);


        ArrayAdapter<String> adapterPatrol = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, patrols);
        adapterPatrol.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        patrol.setAdapter(adapterPatrol);

        if(editMode){
            ViewGroup parent = (ViewGroup) verticalProgression.getParent();
            if(parent != null) parent.removeView(verticalProgression);
            role.setSelection(adapterRole.getPosition(editScout.getRole()));
            patrol.setSelection(adapterPatrol.getPosition(editScout.getPatrol()));
            name.setText(editScout.getName());
            calendar.setTime(editScout.getBirthDay());
            updateLabel();
            if(editScout.getImageUri()!=null)
                Glide.with(this).load(editScout.getImageUri()).into(imageView);

        }
        else {
            ArrayAdapter<CharSequence> adapterProgression = ArrayAdapter.createFromResource(this,
                    R.array.progressionArray, android.R.layout.simple_spinner_item);
            adapterProgression.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            verticalProgression.setAdapter(adapterProgression);
        }


        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
                if (editMode) {
                    database.scoutDao().updateBirthday(editScout.getId(), calendar.getTime());
                }
            }
        };

        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddScoutActivity.this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        patrol.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == adapterPatrol.getCount()-1) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    newPatrolFragment = new NewPatrolFragment();
                    fragmentTransaction.replace(R.id.addScoutFrame, newPatrolFragment);
                    fragmentTransaction.commit();
                    newPatrolFlag = true;
                }
                else{
                    if(editMode)
                        database.scoutDao().updatePatrol(editScout.getId(),patrol.getSelectedItem().toString());
                    if (newPatrolFlag) {
                        getSupportFragmentManager().beginTransaction().remove(newPatrolFragment).commit();
                        newPatrolFlag = false;
                        newPatrolFragment = null;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editMode) {
                    Scout newScout;
                    if (newPatrolFlag) {
                        newScout = new Scout(name.getText().toString(),
                                calendar.getTime(), ((EditText) frameLayout.findViewById
                                (R.id.addScoutNewPatrol)).getText().toString(),
                                role.getSelectedItem().toString(), imageString);
                    } else {
                        newScout = new Scout(name.getText().toString(),
                                calendar.getTime(), patrol.getSelectedItem().toString(),
                                role.getSelectedItem().toString(), imageString);
                    }
                    database.scoutDao().insert(newScout);
                    int id = database.scoutDao().getId(newScout.getName(), newScout.getPatrol(), newScout
                            .getRole(), newScout.getBirthDay());
                    Promise promise = new Promise(id);
                    SecondClass secondClass = new SecondClass(id);
                    FirstClass firstClass = new FirstClass(id);
                    switch (verticalProgression.getSelectedItemPosition()) {
                        case 0:
                            break;
                        case 1:
                            promise.setFinished();
                            break;
                        case 2:
                            promise.setFinished();
                            secondClass.setFinished();
                            break;
                        case 3:
                            promise.setFinished();
                            secondClass.setFinished();
                            firstClass.setFinished();
                            break;
                    }
                    database.promiseDao().insert(promise);
                    database.secondDao().insert(secondClass);
                    database.firstDao().insert(firstClass);
                }
                else{
                    ScoutDao scoutDao = database.scoutDao();
                    if (newPatrolFlag)
                        scoutDao.updatePatrol(editScout.getId(), ((EditText) frameLayout.findViewById
                                (R.id.addScoutNewPatrol)).getText().toString());
                    scoutDao.updateName(editScout.getId(), name.getText().toString());
                }
                finish();
            }
        });

        ActivityResultLauncher<String> getPhoto = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                if(result != null) {
                    imageString = result.toString();
                    Glide.with(AddScoutActivity.this).load(imageString).into(imageView);
                    if(editMode)
                        database.scoutDao().updateImage(editScout.getId(), imageString);
                }
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPhoto.launch("image/*");
            }
        });

    }
    private void initializeFields() {
        this.database = RoomDB.getInstance(this);
        this.name = findViewById(R.id.addScoutName);
        this.birthday = findViewById(R.id.addScoutBirth);
        this.patrol = findViewById(R.id.addScoutPatrolSpinner);
        this.role = findViewById(R.id.addScoutRole);
        this.verticalProgression = findViewById(R.id.addScoutVerticalProgression);
        this.confirm = findViewById(R.id.addScoutConfirmButton);
        this.calendar = Calendar.getInstance();
        this.patrols = database.scoutDao().getPatrols();
        this.patrols.add(getString(R.string.newPatrol));
        this.frameLayout = findViewById(R.id.addScoutFrame);
        this.image = findViewById(R.id.addScoutImage);
        this.imageView = findViewById(R.id.addImageLayout);
        this.editMode = getIntent().getBooleanExtra("edit_mode", false);
        if (editMode) {
            this.editScout = database.scoutDao().findByScoutId(getIntent().getIntExtra("scout_ID", -1));
        }
    }
    private void updateLabel() {
        String myFormat = "dd MMM yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ITALIAN);
        birthday.setText(sdf.format(calendar.getTime()));
    }
}