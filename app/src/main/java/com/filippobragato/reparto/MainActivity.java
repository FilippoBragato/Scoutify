package com.filippobragato.reparto;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.filippobragato.reparto.RecViewAdapter.PatrolRecViewAdapter;
import com.filippobragato.reparto.backend.Department;
import com.filippobragato.reparto.backend.Scout;
import com.filippobragato.reparto.database.RoomDB;
import com.filippobragato.reparto.database.ScoutDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Department department;
    private RecyclerView patrolsRecView;
    private PatrolRecViewAdapter adapter;
    private boolean selectedCardFlag = false;
    private CardView card;
    private Scout selectedScout;

    private RoomDB database;

    public boolean isCardFlagOn() {
        return selectedCardFlag;
    }

    public void setSelectedCardFlag(boolean selectedCardFlag) {
        this.selectedCardFlag = selectedCardFlag;
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO: fare due frammenti, uno per la pattuglia e uno per i pattuglianti da gestire entrambi con tableLayout
        database = RoomDB.getInstance(this);
        initializeDepartment();

        //      CARD ACTIVITY PART      //

        patrolsRecView = findViewById(R.id.patrolRecyclerView);
        adapter = new PatrolRecViewAdapter(department.getPatrols(), this);
        patrolsRecView.setAdapter(adapter);
        patrolsRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        FloatingActionButton add = findViewById(R.id.addScoutFloatingButton);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddScoutActivity.class));
            }
        });
    }

    private void initializeDepartment() {
        ScoutDao scoutDao = database.scoutDao();
        department = new Department();
        String[] roles = getResources().getStringArray(R.array.roleInPatrol);
        for (String role:roles){
            for (Scout scout : scoutDao.getAllByRole(role)) {
                if (scout.isAlive())
                    department.addScout(scout);
            }
        }
    }

    @Override
    protected void onRestart() {
        initializeDepartment();
        adapter.setPatrols(department.getPatrols());
        super.onRestart();
    }

    public void toggleSelectedModeOn(CardView v, Scout s) {
        selectedCardFlag = true;
        this.card = v;
        this.selectedScout = s;
        invalidateOptionsMenu();
    }

    public void toggleSelectedModeOff(){
        selectedCardFlag = false;
        card.setCardBackgroundColor(getResources().getColor(R.color.white));
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
        else {
            inflater.inflate(R.menu.main_department_menu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.share_menu) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, new Gson().toJson(department));
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
            return super.onOptionsItemSelected(item);
        }
        if (id == R.id.delete_menu) {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.deleteEntry))
                    .setMessage(R.string.sure)

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            department.removeScout(selectedScout, database);
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
        if(id == R.id.gone_menu) {
            department.setGone(selectedScout);
            database.scoutDao().updateGone(selectedScout.getId(), true);
            Objects.requireNonNull(patrolsRecView.getAdapter()).notifyDataSetChanged();
            invalidateOptionsMenu();
            toggleSelectedModeOff();
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
        return super.onOptionsItemSelected(item);

    }

}