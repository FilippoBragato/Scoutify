package com.filippobragato.reparto;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.view.ActionMode;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.filippobragato.reparto.RecViewAdapter.PatrolRecViewAdapter;
import com.filippobragato.reparto.backend.Department;
import com.filippobragato.reparto.backend.FirstClass;
import com.filippobragato.reparto.backend.Note;
import com.filippobragato.reparto.backend.Patrol;
import com.filippobragato.reparto.backend.Promise;
import com.filippobragato.reparto.backend.Score;
import com.filippobragato.reparto.backend.Scout;
import com.filippobragato.reparto.backend.SecondClass;
import com.filippobragato.reparto.database.RoomDB;
import com.filippobragato.reparto.database.ScoutDao;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private Department department;
    private RecyclerView patrolsRecView;
    private PatrolRecViewAdapter adapter;
    private boolean selectedCardFlag = false;
    private MaterialCardView card;
    private Scout selectedScout;

    private RoomDB database;

    public boolean isCardFlagOn() {
        return selectedCardFlag;
    }

    public void setSelectedCardFlag(boolean selectedCardFlag) {
        this.selectedCardFlag = selectedCardFlag;
    }

    private void logIn(){
        ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
                new FirebaseAuthUIActivityResultContract(),
                new ActivityResultCallback<FirebaseAuthUIAuthenticationResult>() {
                    @Override
                    public void onActivityResult(FirebaseAuthUIAuthenticationResult result) {
                    }
                }
        );
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build());

        // Create and launch sign-in intent
        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTheme(R.style.Theme_Reparto)
                .build();
        signInLauncher.launch(signInIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        if(FirebaseAuth.getInstance().getCurrentUser() == null && savedInstanceState == null){
            logIn();
        }
        //TODO: fare due frammenti, uno per la pattuglia e uno per i pattuglianti da gestire entrambi con tableLayout
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        database = RoomDB.getInstance(this);
        initializeDepartment();

        FloatingActionButton add = findViewById(R.id.addScoutFloatingButton);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedCardFlag){
                    invalidateOptionsMenu();
                    toggleSelectedModeOff();
                }
                startActivity(new Intent(MainActivity.this, AddScoutActivity.class));
            }
        });
    }

    private void initializeDepartment() {
        ScoutDao scoutDao = database.scoutDao();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        firestore.collection("lissaro").document("summary").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        Map<String, Object> summary = document.getData();
                        Set<String> ids = summary.keySet();
                        for (String string_id : ids) {
                            int id = Integer.parseInt(string_id);
                            Scout scout = scoutDao.findByScoutId(id);
                            Map<String, Object> details = (Map<String, Object>) summary.get(string_id);
                            if(scout == null) {
                                scoutDao.insert(new Scout(id, (String) details.get("name"),(String) details.get("patrol"),(String) details.get("role"),(boolean) details.get("gone")));
                            }
                            else{
                                scoutDao.updatePatrol(id,(String) details.get("patrol"));
                                scoutDao.updateName(id, (String) details.get("name"));
                                scoutDao.updateRole(id, (String) details.get("role"));
                                scoutDao.updateGone(id, (boolean) details.get("gone"));
                            }
                        }
                    }
                }
                department = new Department();
                String[] roles = getResources().getStringArray(R.array.roleInPatrol);
                for (String role:roles){
                    for (Scout scout : scoutDao.getAllByRole(role)) {
                        if (!scout.isGone())
                            department.addScout(scout);
                    }
                }
                patrolsRecView = findViewById(R.id.patrolRecyclerView);
                adapter = new PatrolRecViewAdapter(department.getPatrols(), MainActivity.this);
                patrolsRecView.setAdapter(adapter);
                patrolsRecView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
            }
        });

    }


    public void toggleSelectedModeOn(MaterialCardView v, Scout s) {
        selectedCardFlag = true;
        this.card = v;
        this.selectedScout = s;
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle(selectedScout.getName() + " selected");
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_clear);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        invalidateOptionsMenu();
    }

    public void toggleSelectedModeOff(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle(R.string.app_name);
        selectedCardFlag = false;
        card.setChecked(false);
        this.card = null;
        this.selectedScout = null;
    }

    @Override
    public void onBackPressed() {
        if(selectedCardFlag) {
            toggleSelectedModeOff();
            invalidateOptionsMenu();
        }
        else super.onBackPressed();
    }

    @Override
    protected void onResume() {
        invalidateOptionsMenu();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        if(selectedCardFlag) {
            inflater.inflate(R.menu.itemselected_department_menu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.delete_menu) {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.deleteEntry))
                    .setMessage(R.string.sure)

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            department.removeScout(selectedScout);
                            FirebaseFirestore.getInstance().collection("lissaro").document("summary").update(selectedScout.getId()+".gone", true);
                            Objects.requireNonNull(patrolsRecView.getAdapter()).notifyDataSetChanged();
                            invalidateOptionsMenu();
                            toggleSelectedModeOff();
                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton(android.R.string.no, null)
                    .show();
            return super.onOptionsItemSelected(item);
        }
        if(id == R.id.edit_menu) {
            Intent intent = new Intent(this, AddScoutActivity.class).putExtra("edit_mode", true).putExtra("scout_ID", selectedScout.getId());
            startActivity(intent);
            Objects.requireNonNull(patrolsRecView.getAdapter()).notifyDataSetChanged();
            invalidateOptionsMenu();
            toggleSelectedModeOff();
            return super.onOptionsItemSelected(item);
        }
        if (id == android.R.id.home) {
            toggleSelectedModeOff();
            invalidateOptionsMenu();
            return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);

    }

}