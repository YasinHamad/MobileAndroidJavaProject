package com.yasin.hamad27.mobileandroidjavaproject.ui.add;

import static com.yasin.hamad27.mobileandroidjavaproject.MainActivity.db;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.yasin.hamad27.mobileandroidjavaproject.R;
import com.yasin.hamad27.mobileandroidjavaproject.database.Task;

public class AddTaskActivity extends AppCompatActivity {

    EditText etTitle, etDescription, etDate, etStartingTime, etDuration;
    CheckBox Reading, Summarizing, Homework, Search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_task);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etTitle = findViewById(R.id.etTitle);
        etDescription = findViewById(R.id.etDescription);
        etDate = findViewById(R.id.etTaskDate);
        etStartingTime = findViewById(R.id.etTaskStartingTime);
        etDuration = findViewById(R.id.etTaskDuration);

        Reading = findViewById(R.id.Reading);
        Summarizing = findViewById(R.id.Summarizing);
        Homework = findViewById(R.id.Homework);
        Search = findViewById(R.id.Search);
    }

    public void InsertTask(View view) {

        String title = etTitle.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        String date = etDate.getText().toString().trim();
        String startingTime = etStartingTime.getText().toString().trim();
        String dur = etDuration.getText().toString().trim();

        if (title.isEmpty() || description.isEmpty() || date.isEmpty()
                || startingTime.isEmpty() || dur.isEmpty()) {

            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        String type = "";
        if (Reading.isChecked()) type += "Reading ";
        if (Summarizing.isChecked()) type += "Summarizing ";
        if (Homework.isChecked()) type += "Homework ";
        if (Search.isChecked()) type += "Search ";

        Task task = new Task();
        task.title = title;
        task.description = description;
        task.date = date;
        task.startingTime = startingTime;
        task.duration = Integer.parseInt(dur);
        task.type = type;

        db.taskDao().insert(task);

        Toast.makeText(this, "Task inserted successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}