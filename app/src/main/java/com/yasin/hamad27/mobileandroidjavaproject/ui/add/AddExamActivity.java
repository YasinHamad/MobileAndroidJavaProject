package com.yasin.hamad27.mobileandroidjavaproject.ui.add;

import static com.yasin.hamad27.mobileandroidjavaproject.MainActivity.db;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.yasin.hamad27.mobileandroidjavaproject.R;
import com.yasin.hamad27.mobileandroidjavaproject.database.Exam;

public class AddExamActivity extends AppCompatActivity {

    EditText etName, etCourse, etSeat, etRoom,
            etExamDate, etExamStartingTime, etExamDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_add_exam);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etName = findViewById(R.id.etName);
        etCourse = findViewById(R.id.etCourse);
        etSeat = findViewById(R.id.etSeat);
        etRoom = findViewById(R.id.etRoom);
        etExamDate = findViewById(R.id.etExamDate);
        etExamStartingTime = findViewById(R.id.etExamStartingTime);
        etExamDuration = findViewById(R.id.etExamDuration);
    }

    public void InsertExam(View view) {

        String name = etName.getText().toString().trim();
        String course = etCourse.getText().toString().trim();
        String seat = etSeat.getText().toString().trim();
        String room = etRoom.getText().toString().trim();
        String date = etExamDate.getText().toString().trim();
        String startingTime = etExamStartingTime.getText().toString().trim();
        String dur = etExamDuration.getText().toString().trim();

        if (name.isEmpty() || course.isEmpty() || seat.isEmpty()
                || room.isEmpty() || date.isEmpty()
                || startingTime.isEmpty() || dur.isEmpty()) {

            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Exam exam = new Exam();
        exam.examName = name;
        exam.course = course;
        exam.seatNumber = seat;
        exam.roomNumber = room;
        exam.date = date;
        exam.startingTime = startingTime;
        exam.duration = Integer.parseInt(dur);

        db.examDao().insert(exam);

        Toast.makeText(this, "Exam inserted successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}
