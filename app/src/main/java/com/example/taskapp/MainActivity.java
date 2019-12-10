package com.example.taskapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    ListView taskList;
    ArrayList<String> tasks = new ArrayList<>();
    ArrayList<String> times = new ArrayList<>();

    String timeDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // View for containing tasks
        taskList = findViewById(R.id.taskList);
        final Adapter adapter = new Adapter(MainActivity.this, tasks, times);
        taskList.setAdapter(adapter);

        // Code for addTask btn to display dialog for adding to list
        Button addTask = findViewById(R.id.addTask);
        Button removeAll = findViewById(R.id.removeAll);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // For adding chunks
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_add_task, null);
                final EditText textInput = mView.findViewById(R.id.textInput);
                final Button setTime = mView.findViewById(R.id.setTime);
                Button addNewTask = mView.findViewById(R.id.addNewTask);

                builder.setView(mView);
                final AlertDialog dialog = builder.create();
                dialog.show();

                // For showing time picker
                setTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DialogFragment timePicker = new TimePickerFragment();
                        timePicker.show(getSupportFragmentManager(), "time picker");
                    }
                });

                // For clicking to add a new task
                addNewTask.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!textInput.getText().toString().isEmpty() && timeDisplay != null) {
                            tasks.add(textInput.getText().toString());
                            times.add(timeDisplay);
                            timeDisplay = null;
                            adapter.notifyDataSetChanged();
                            Toast.makeText(MainActivity.this,
                                    "Added to list",
                                    Toast.LENGTH_SHORT).show();
                            // At the end upon successful input
                            dialog.hide();
                        } else {
                            Toast.makeText(MainActivity.this,
                                    "Fill out all fields",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        removeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tasks.clear();
                times.clear();
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        timeDisplay = hourOfDay + ":" + minute;
    }
}
