package com.yasin.hamad27.mobileandroidjavaproject.ui.add;

import static com.yasin.hamad27.mobileandroidjavaproject.MainActivity.db;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.yasin.hamad27.mobileandroidjavaproject.R;
import com.yasin.hamad27.mobileandroidjavaproject.database.Exam;
import com.yasin.hamad27.mobileandroidjavaproject.databinding.ActivityAddExamBinding;
import com.yasin.hamad27.mobileandroidjavaproject.databinding.ActivityAddTaskBinding;

public class AddExamActivity extends AppCompatActivity {
    private ActivityAddExamBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddExamBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }

    public void InsertExam(View view) {
        String dur=binding.etExamDuration.getText().toString();

        Exam exam=new Exam();
        exam.examName=binding.etName.getText().toString();
        exam.course=binding.etCourse.getText().toString();
        exam.seatNumber=binding.etSeat.getText().toString();
        exam.roomNumber=binding.etRoom.getText().toString();
        exam.date=binding.etExamDate.getText().toString();
        exam.startingTime=binding.etExamStartingTime.getText().toString();
        exam.duration=Integer.parseInt(dur);
        db.examDao().insert(exam);
        Toast.makeText(this,"Exam Inserted successfully",Toast.LENGTH_SHORT).show();
        finish();
    }
}
