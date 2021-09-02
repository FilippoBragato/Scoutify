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
import com.filippobragato.reparto.database.SpecialityDao;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SpecialityRecViewAdapter extends RecyclerView.Adapter<SpecialityRecViewAdapter.ViewHolder> {

    private List<Speciality> specialities;
    private final String[] name;
    private final String[] testsBody;
    private RoomDB database;

    //TODO TOGGLE SCROLLABLE OFF
    public SpecialityRecViewAdapter(List<Speciality> specialities, String[] name, String[] tests) {
        this.specialities = specialities;
        this.name = name;
        this.testsBody = tests;
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
        database = RoomDB.getInstance(view.getContext());
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SpecialityRecViewAdapter.ViewHolder holder, int position) {
        holder.title.setText(name[specialities.get(position).getId_name()]);
        int start = specialities.get(position).getId_name()*4;
        for (int i = 0; i < holder.tests.length; i++) {
            holder.tests[i].setText(testsBody[start+i]);
        }
        holder.tests[4].setText(testsBody[104]);

        SpecialityDao specialityDao = database.specialityDao();
        holder.tests[0].setChecked(specialities.get(position).isTest0());
        holder.tests[0].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                specialities.get(holder.getAdapterPosition()).setTest0(isChecked);
                specialityDao.updateTest0(specialities.get(holder.getAdapterPosition()).getScout_ID(),
                        specialities.get(holder.getAdapterPosition()).getId_name(), isChecked);
            }
        });
        holder.tests[1].setChecked(specialities.get(position).isTest1());
        holder.tests[1].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                specialities.get(holder.getAdapterPosition()).setTest1(isChecked);
                specialityDao.updateTest1(specialities.get(holder.getAdapterPosition()).getScout_ID(),
                        specialities.get(holder.getAdapterPosition()).getId_name(), isChecked);
            }
        });
        holder.tests[2].setChecked(specialities.get(position).isTest2());
        holder.tests[2].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                specialities.get(holder.getAdapterPosition()).setTest2(isChecked);
                specialityDao.updateTest2(specialities.get(holder.getAdapterPosition()).getScout_ID(),
                        specialities.get(holder.getAdapterPosition()).getId_name(), isChecked);
            }
        });
        holder.tests[3].setChecked(specialities.get(position).isTest3());
        holder.tests[3].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                specialities.get(holder.getAdapterPosition()).setTest3(isChecked);
                specialityDao.updateTest3(specialities.get(holder.getAdapterPosition()).getScout_ID(),
                        specialities.get(holder.getAdapterPosition()).getId_name(), isChecked);
            }
        });
        holder.tests[4].setChecked(specialities.get(position).isTest4());
        holder.tests[4].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                specialities.get(holder.getAdapterPosition()).setTest4(isChecked);
                specialityDao.updateTest4(specialities.get(holder.getAdapterPosition()).getScout_ID(),
                        specialities.get(holder.getAdapterPosition()).getId_name(), isChecked);
            }
        });
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
