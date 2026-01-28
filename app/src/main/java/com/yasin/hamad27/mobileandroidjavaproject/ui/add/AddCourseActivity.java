package com.yasin.hamad27.mobileandroidjavaproject.ui.add;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

        // Initialize views
        initializeViews();

        // Set up save button click listener
        buttonSaveCourse.setOnClickListener(v -> saveCourse());
    }

    private void initializeViews() {
        editTextCourseTitle = findViewById(R.id.editTextCourseTitle);
        editTextCourseDescription = findViewById(R.id.editTextCourseDescription);
        buttonSaveCourse = findViewById(R.id.buttonSaveCourse);
    }

    private void saveCourse() {
        // Get input values
        String title = editTextCourseTitle.getText().toString().trim();
        String description = editTextCourseDescription.getText().toString().trim();

        // Validate required fields
        if (title.isEmpty()) {
            Toast.makeText(this, "Please enter course title", Toast.LENGTH_SHORT).show();
            editTextCourseTitle.requestFocus();
            return;
        }

        if (description.isEmpty()) {
            Toast.makeText(this, "Please enter course description", Toast.LENGTH_SHORT).show();
            editTextCourseDescription.requestFocus();
            return;
        }

        // Create Course object
        Course course = new Course();
        course.title = title;
        course.description = description;
        course.isDone = false;

        // Insert into database in background thread
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                MainActivity.db.courseDao().insert(course);

                // Show success message on UI thread
                runOnUiThread(() -> {
                    Toast.makeText(AddCourseActivity.this,
                            "Course saved successfully!",
                            Toast.LENGTH_SHORT).show();

                    // Close activity and go back
                    finish();
                });
            } catch (Exception e) {
                // Show error message on UI thread
                runOnUiThread(() -> {
                    Toast.makeText(AddCourseActivity.this,
                            "Error saving course: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                });
            }
        });

        executor.shutdown();
    }
}