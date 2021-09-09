package com.filippobragato.reparto.RecViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.filippobragato.reparto.R;
import com.filippobragato.reparto.backend.Speciality;
import com.filippobragato.reparto.database.RoomDB;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SpecialityRecViewAdapter extends RecyclerView.Adapter<SpecialityRecViewAdapter.ViewHolder> {

    private List<Speciality> specialities;
    private final String[] name;
    private final String[] testsBody;
    private int id;
    private DocumentReference doc;

    public SpecialityRecViewAdapter(List<Speciality> specialities, String[] name, String[] tests, int id) {
        this.specialities = specialities;
        this.name = name;
        this.testsBody = tests;
        this.id = id;
        notifyDataSetChanged();
    }

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
        notifyDataSetChanged();
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.speciality_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SpecialityRecViewAdapter.ViewHolder holder, int position) {
        holder.title.setText(name[specialities.get(position).getId()]);
        int start = specialities.get(position).getId()*4;
        for (int i = 0; i < holder.tests.length; i++) {
            holder.tests[i].setText(testsBody[start+i]);
        }
        holder.tests[4].setText(testsBody[104]);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        doc = db.collection("lissaro").document("summary")
                .collection("scout").document(Integer.toString(id))
                .collection("extra").document("progression");
        holder.tests[0].setChecked(specialities.get(position).isTest0());
        holder.tests[0].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doc.update("specialities", FieldValue.arrayRemove(specialities.get(holder.getAdapterPosition())));
                specialities.get(holder.getAdapterPosition()).setTest0(isChecked);
                updateDB(holder, db);
            }
        });
        holder.tests[1].setChecked(specialities.get(position).isTest1());
        holder.tests[1].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doc.update("specialities", FieldValue.arrayRemove(specialities.get(holder.getAdapterPosition())));
                specialities.get(holder.getAdapterPosition()).setTest1(isChecked);
                updateDB(holder, db);
            }
        });
        holder.tests[2].setChecked(specialities.get(position).isTest2());
        holder.tests[2].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doc.update("specialities", FieldValue.arrayRemove(specialities.get(holder.getAdapterPosition())));
                specialities.get(holder.getAdapterPosition()).setTest2(isChecked);
                updateDB(holder, db);
            }
        });
        holder.tests[3].setChecked(specialities.get(position).isTest3());
        holder.tests[3].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doc.update("specialities", FieldValue.arrayRemove(specialities.get(holder.getAdapterPosition())));
                specialities.get(holder.getAdapterPosition()).setTest3(isChecked);
                updateDB(holder, db);
            }
        });
        holder.tests[4].setChecked(specialities.get(position).isTest4());
        holder.tests[4].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doc.update("specialities", FieldValue.arrayRemove(specialities.get(holder.getAdapterPosition())));
                specialities.get(holder.getAdapterPosition()).setTest4(isChecked);
                updateDB(holder, db);
            }
        });
    }

    private void updateDB(@NonNull @NotNull ViewHolder holder, FirebaseFirestore db) {
        if (specialities.get(holder.getAdapterPosition()).isFinished()) {
            db.collection("lissaro").document("summary")
                    .collection("scout").document(Integer.toString(id)).update("specialities",
                    FieldValue.arrayUnion(name[specialities.get(holder.getAdapterPosition()).getId()]));
        } else {
            doc.update("specialities", FieldValue.arrayUnion(specialities.get(holder.getAdapterPosition())));
        }
    }

    @Override
    public int getItemCount() {
        return specialities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private CheckBox[] tests;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.specialityName);
            tests = new CheckBox[5];
            tests[0] = itemView.findViewById(R.id.checkboxFirstTest);
            tests[1] = itemView.findViewById(R.id.checkboxSecondTest);
            tests[2] = itemView.findViewById(R.id.checkboxThirdTest);
            tests[3] = itemView.findViewById(R.id.checkboxFourthTest);
            tests[4] = itemView.findViewById(R.id.checkboxFreeTest);
        }
    }
}
