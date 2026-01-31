package com.yasin.hamad27.mobileandroidjavaproject.ui.add;

import static com.yasin.hamad27.mobileandroidjavaproject.MainActivity.db;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.text.TextUtils;

import com.yasin.hamad27.mobileandroidjavaproject.MainActivity;
import com.yasin.hamad27.mobileandroidjavaproject.R;
import com.yasin.hamad27.mobileandroidjavaproject.database.Lecture;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddLectureActivity extends AppCompatActivity {

    private EditText editTextLectureName, editTextBuildingName, editTextRoomNumber,
            editTextTeacher, editTextCourse, editTextDate,
            editTextStartingTime, editTextEndingTime;
    private RadioGroup radioGroupAttendance;
    private RadioButton radioInPerson, radioOnline;
    private Button buttonSaveLecture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_lecture);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getSupportActionBar().setTitle("Add Lecture");


        initializeViews();

        buttonSaveLecture.setOnClickListener(v -> saveLecture());
    }

    private void initializeViews() {
        editTextLectureName = findViewById(R.id.editTextLectureName);
        editTextBuildingName = findViewById(R.id.editTextBuildingName);
        editTextRoomNumber = findViewById(R.id.editTextRoomNumber);
        editTextTeacher = findViewById(R.id.editTextTeacher);
        editTextCourse = findViewById(R.id.editTextCourse);
        editTextDate = findViewById(R.id.editTextDate);
        editTextStartingTime = findViewById(R.id.editTextStartingTime);
        editTextEndingTime = findViewById(R.id.editTextEndingTime);
        radioGroupAttendance = findViewById(R.id.radioGroupAttendance);
        radioInPerson = findViewById(R.id.radioInPerson);
        radioOnline = findViewById(R.id.radioOnline);
        buttonSaveLecture = findViewById(R.id.buttonSaveLecture);
    }

    private void saveLecture() {

        String lectureName = editTextLectureName.getText().toString().trim();
        String buildingName = editTextBuildingName.getText().toString().trim();
        String roomNumber = editTextRoomNumber.getText().toString().trim();
        String teacher = editTextTeacher.getText().toString().trim();
        String course = editTextCourse.getText().toString().trim();
        String date = editTextDate.getText().toString().trim();
        String startingTime = editTextStartingTime.getText().toString().trim();
        String endingTime = editTextEndingTime.getText().toString().trim();

        int selectedRadioId = radioGroupAttendance.getCheckedRadioButtonId();
        String attendanceType = "";
        if (selectedRadioId == R.id.radioInPerson) {
            attendanceType = "In Person";
        } else if (selectedRadioId == R.id.radioOnline) {
            attendanceType = "Online";
        }

        if (TextUtils.isEmpty(lectureName) || TextUtils.isEmpty(buildingName)
                || TextUtils.isEmpty(roomNumber) || TextUtils.isEmpty(teacher)
                || TextUtils.isEmpty(course) || TextUtils.isEmpty(date)
                || TextUtils.isEmpty(startingTime) || TextUtils.isEmpty(endingTime)) {

            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Lecture lecture = new Lecture();
        lecture.lectureName = lectureName;
        lecture.buildingName = buildingName;
        lecture.roomNumber = roomNumber;
        lecture.teacher = teacher;
        lecture.course = course;
        lecture.date = date;
        lecture.startingTime = startingTime;
        lecture.endingTime = endingTime;
        lecture.type = attendanceType;
        lecture.isDone = false;
        db.lectureDao().insert(lecture);
        Toast.makeText(this, "Lecture inserted successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}