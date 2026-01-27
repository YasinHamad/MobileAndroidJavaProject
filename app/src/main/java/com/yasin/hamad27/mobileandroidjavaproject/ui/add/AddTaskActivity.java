package com.yasin.hamad27.mobileandroidjavaproject.ui.add;

import static com.yasin.hamad27.mobileandroidjavaproject.MainActivity.db;

import android.content.Intent;
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

import com.yasin.hamad27.mobileandroidjavaproject.MainActivity;
import com.yasin.hamad27.mobileandroidjavaproject.R;
import com.yasin.hamad27.mobileandroidjavaproject.database.AppDatabase;
import com.yasin.hamad27.mobileandroidjavaproject.database.Task;
import com.yasin.hamad27.mobileandroidjavaproject.databinding.ActivityAddTaskBinding;
import com.yasin.hamad27.mobileandroidjavaproject.ui.home.HomeFragment;

public class AddTaskActivity extends AppCompatActivity {

   private ActivityAddTaskBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }

    public void InsertTask(View view) {
        String type="";

        if(binding.Reading.isChecked()){
            type+="Reading ";
        }
        if(binding.Summarizing.isChecked()){
            type+="Summarizing ";
        }
        if(binding.Homework.isChecked()){
            type+="HomeWork ";
        }
        if(binding.Search.isChecked()){
            type+="Search ";
        }
        String dur;

        dur=binding.etTaskDuration.getText().toString();

        Task task = new Task();
        task.title = binding.etTitle.getText().toString();
        task.description = binding.etDescription.getText().toString();
        task.date = binding.etTaskDate.getText().toString();
        task.startingTime = binding.etTaskStartingTime.getText().toString();
        task.duration = Integer.parseInt(dur);
        task.type = type;
        db.taskDao().insert(task);
        Toast.makeText(this, "Task inserted successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}