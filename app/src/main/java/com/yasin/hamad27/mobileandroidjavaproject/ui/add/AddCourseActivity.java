package com.yasin.hamad27.mobileandroidjavaproject.ui.add;

import static com.yasin.hamad27.mobileandroidjavaproject.MainActivity.db;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.text.TextUtils;

import com.yasin.hamad27.mobileandroidjavaproject.MainActivity;
import com.yasin.hamad27.mobileandroidjavaproject.R;
import com.yasin.hamad27.mobileandroidjavaproject.database.Course;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddCourseActivity extends AppCompatActivity {

    private EditText editTextCourseTitle, editTextCourseDescription;
    private Button buttonSaveCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_course);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getSupportActionBar().setTitle("Add Course");
        initializeViews();

        buttonSaveCourse.setOnClickListener(v -> saveCourse());
    }

    private void initializeViews() {
        editTextCourseTitle = findViewById(R.id.editTextCourseTitle);
        editTextCourseDescription = findViewById(R.id.editTextCourseDescription);
        buttonSaveCourse = findViewById(R.id.buttonSaveCourse);
    }

    private void saveCourse() {

        String title = editTextCourseTitle.getText().toString().trim();
        String description = editTextCourseDescription.getText().toString().trim();

        if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description)) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }


        Course course = new Course();
        course.title = title;
        course.description = description;
        course.isDone = false;
        db.courseDao().insert(course);
        Toast.makeText(this, "Course inserted successfully", Toast.LENGTH_SHORT).show();
        finish();
    }


    }