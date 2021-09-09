package com.filippobragato.reparto;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
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
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.canhub.cropper.CropImageContract;
import com.canhub.cropper.CropImageContractOptions;
import com.canhub.cropper.CropImageOptions;
import com.canhub.cropper.CropImageView;
import com.filippobragato.reparto.backend.FirstClass;
import com.filippobragato.reparto.backend.Progression;
import com.filippobragato.reparto.backend.Promise;
import com.filippobragato.reparto.backend.Scout;
import com.filippobragato.reparto.backend.SecondClass;
import com.filippobragato.reparto.database.RoomDB;
import com.filippobragato.reparto.database.ScoutDao;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class AddScoutActivity extends AppCompatActivity {

    private TextInputLayout name, birthday;
    private TextInputEditText birthdayEdit;
    private AutoCompleteTextView patrol, role, verticalProgression;
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
    private DocumentReference docSummary;
    private DocumentReference docScout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_scout);
        initializeFields();


        if(editMode){
            ViewGroup parent = (ViewGroup) verticalProgression.getParent();
            if(parent != null) parent.removeView(verticalProgression);
            TextInputLayout verticalContainer = findViewById(R.id.addScoutVerticalProgressionSpinnerContainer);
            parent = (ViewGroup) verticalContainer.getParent();
            if(parent != null) parent.removeView(verticalContainer);
            docScout.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()){
                        DocumentSnapshot documentSnapshot = task.getResult();
                        if(documentSnapshot.exists()){
                            editScout = documentSnapshot.toObject(Scout.class);
                            role.setText(editScout.getRole(), false);
                            patrol.setText(editScout.getPatrol(),false);
                            name.getEditText().setText(editScout.getName());
                            calendar.setTime(editScout.getBirthDay());
                            updateLabel();
                            editScout.setImageUri(database.scoutDao().findByScoutId(editScout.getId()).getImageUri());
                            if(editScout.getImageUri()!=null)
                                Glide.with(AddScoutActivity.this).load(editScout.getImageUri()).into(imageView);
                        }
                    }
                }
            });
        }
        else {
            ArrayAdapter<CharSequence> adapterProgression = ArrayAdapter.createFromResource(this,
                    R.array.progressionArray, android.R.layout.simple_spinner_dropdown_item);
            adapterProgression.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            verticalProgression.setAdapter(adapterProgression);
        }
        ArrayAdapter<CharSequence> adapterRole = ArrayAdapter.createFromResource(this,
                R.array.roleInPatrol, android.R.layout.simple_spinner_dropdown_item);
        adapterRole.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        role.setAdapter(adapterRole);


        ArrayAdapter<String> adapterPatrol = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, patrols);
        adapterPatrol.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        patrol.setAdapter(adapterPatrol);

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
                if (editMode) {
                    docScout.update("birthDay", calendar.getTime());
                }
            }
        };
        birthdayEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddScoutActivity.this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddScoutActivity.this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        patrol.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == adapterPatrol.getCount()-1) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    newPatrolFragment = new NewPatrolFragment();
                    fragmentTransaction.replace(R.id.addScoutFrame, newPatrolFragment);
                    fragmentTransaction.commit();
                    newPatrolFlag = true;
                }
                else{
                    if(editMode) {
                        docScout.update("patrol", patrol.getEditableText().toString());
                        docSummary.update(editScout.getId()+".patrol",patrol.getEditableText().toString());
                        database.scoutDao().updatePatrol(editScout.getId(), patrol.getEditableText().toString());
                    }
                    if (newPatrolFlag) {
                        getSupportFragmentManager().beginTransaction().remove(newPatrolFragment).commit();
                        newPatrolFlag = false;
                        newPatrolFragment = null;
                    }
                }
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editMode) {
                    Scout newScout;
                    if (newPatrolFlag) {
                        newScout = new Scout(name.getEditText().getText().toString(),
                                calendar.getTime(), ((TextInputLayout) frameLayout.findViewById
                                (R.id.addScoutNewPatrol)).getEditText().getText().toString(),
                                role.getEditableText().toString(), imageString);
                    } else {
                        newScout = new Scout(name.getEditText().getText().toString(),
                                calendar.getTime(), patrol.getEditableText().toString(),
                                role.getEditableText().toString(), imageString);
                    }
                    database.scoutDao().insert(newScout);
                    int id = database.scoutDao().getId(newScout.getName(), newScout.getPatrol(), newScout
                            .getRole());
                    newScout.setId(id);
                    int progressionStatus = 0;
                    String[] progressionArray = getResources().getStringArray(R.array.progressionArray);
                    Progression progression = new Progression();
                    if(verticalProgression.getEditableText().toString().equals(progressionArray[3])){
                        progressionStatus = 3;
                        progression.getFirstClass().setFinished();
                        progression.getSecondClass().setFinished();
                        progression.getPromise().setFinished();

                    }
                    else {
                        if(verticalProgression.getEditableText().toString().equals(progressionArray[2])){
                            progressionStatus = 2;
                            progression.getSecondClass().setFinished();
                            progression.getPromise().setFinished();
                        }
                        else
                            if(verticalProgression.getEditableText().toString().equals(progressionArray[1])){
                                progressionStatus = 1;
                                progression.getPromise().setFinished();
                            }
                    }
                    newScout.setVertical(progressionStatus);
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    Map<String, Object> generality = new HashMap<>();
                    Map<String, Object> scout = new HashMap<>();

                    scout.put("name", newScout.getName());
                    scout.put("role", newScout.getRole());
                    scout.put("patrol", newScout.getPatrol());
                    scout.put("gone", false);
                    generality.put(Integer.toString(newScout.getId()), scout);
                    db.collection("lissaro").document("summary").set(generality, SetOptions.merge());
                    db.collection("lissaro").document("summary").collection("scout").document(Integer.toString(newScout.getId())).set(newScout);
                    db.collection("lissaro").document("summary").collection("scout").document(Integer.toString(id)).collection("extra").document("progression").set(progression);
                }
                else{
                    ScoutDao scoutDao = database.scoutDao();
                    if (newPatrolFlag) {
                        scoutDao.updatePatrol(editScout.getId(), ((TextInputLayout) frameLayout.findViewById
                                (R.id.addScoutNewPatrol)).getEditText().getText().toString());
                        docScout.update("patrol", ((TextInputLayout) frameLayout.findViewById
                                (R.id.addScoutNewPatrol)).getEditText().getText().toString());
                        docSummary.update(editScout.getId()+".patrol",((TextInputLayout) frameLayout.findViewById
                                (R.id.addScoutNewPatrol)).getEditText().getText().toString());

                    }
                    scoutDao.updateName(editScout.getId(), name.getEditText().getText().toString());
                    docScout.update("name", name.getEditText().getText().toString());
                    docSummary.update(editScout.getId()+".name", name.getEditText().getText().toString());
                    scoutDao.updateRole(editScout.getId(), role.getEditableText().toString());
                    docScout.update("role", role.getEditableText().toString());
                    docSummary.update(editScout.getId()+".role", role.getEditableText().toString());
                }
                finish();
            }
        });

        ActivityResultLauncher<CropImageContractOptions> cropLauncher = registerForActivityResult(new CropImageContract(), new ActivityResultCallback<CropImageView.CropResult>() {
            @Override
            public void onActivityResult(CropImageView.CropResult result) {
                if (result.isSuccessful()) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    imageString = result.getUriContent().toString();
                    Glide.with(AddScoutActivity.this).load(imageString).into(imageView);
                    if (editMode)
                        database.scoutDao().updateImage(editScout.getId(), imageString);
                }
            }
        });

        ActivityResultLauncher<String> getPhoto = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                if(result != null) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    CropImageOptions cropImageOptions = new CropImageOptions();
                    cropImageOptions.aspectRatioX = 100;
                    cropImageOptions.aspectRatioY = 100;
                    cropImageOptions.fixAspectRatio = true;
                    cropLauncher.launch(new CropImageContractOptions(result, cropImageOptions));
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
        this.birthdayEdit = findViewById(R.id.addScoutBirthPick);
        this.patrol = findViewById(R.id.addScoutPatrolSpinner);
        this.role = findViewById(R.id.addScoutRoleSpinner);
        this.verticalProgression = findViewById(R.id.addScoutVerticalProgressionSpinner);
        this.confirm = findViewById(R.id.addScoutConfirmButton);
        this.calendar = Calendar.getInstance();
        this.patrols = database.scoutDao().getPatrols();
        this.patrols.add(getString(R.string.new_));
        this.frameLayout = findViewById(R.id.addScoutFrame);
        this.image = findViewById(R.id.addScoutImage);
        this.imageView = findViewById(R.id.addImageLayout);
        this.editMode = getIntent().getBooleanExtra("edit_mode", false);
        if (editMode) {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            this.docSummary = db.collection("lissaro").document("summary");
            this.docScout = docSummary.collection("scout").document(Integer.toString(getIntent().getIntExtra("scout_ID", -1)));
        }
    }
    private void updateLabel() {
        String myFormat = "dd MMM yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ITALIAN);
        birthday.getEditText().setText(sdf.format(calendar.getTime()));
    }
}