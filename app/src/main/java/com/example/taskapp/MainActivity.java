package com.example.taskapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Code for addTask btn to display alert for adding to list
        Button addTask = findViewById(R.id.addTask);
        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_add_task, null);
                final EditText textInput = mView.findViewById(R.id.textInput);
                final EditText timeInput = mView.findViewById(R.id.timeInput);
                Button addNewTask = mView.findViewById(R.id.addNewTask);

                builder.setView(mView);
                final AlertDialog dialog = builder.create();
                dialog.show();

                // For clicking to add a new task
                addNewTask.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!textInput.getText().toString().isEmpty() && !timeInput.getText().toString().isEmpty()) {

                            // At the end upon successful input
                            dialog.hide();
                        } else {
                            Toast.makeText(MainActivity.this,
                                    "Insert into all fields",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
