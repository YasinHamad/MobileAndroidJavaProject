package com.yasin.hamad27.mobileandroidjavaproject.ui.home;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yasin.hamad27.mobileandroidjavaproject.R;
import com.yasin.hamad27.mobileandroidjavaproject.adapters.CourseAdapter;
import com.yasin.hamad27.mobileandroidjavaproject.adapters.ExamAdapter;
import com.yasin.hamad27.mobileandroidjavaproject.adapters.LectureAdapter;
import com.yasin.hamad27.mobileandroidjavaproject.adapters.TaskAdapter;
import com.yasin.hamad27.mobileandroidjavaproject.database.Course;
import com.yasin.hamad27.mobileandroidjavaproject.database.Exam;
import com.yasin.hamad27.mobileandroidjavaproject.database.Lecture;
import com.yasin.hamad27.mobileandroidjavaproject.database.Task;
import com.yasin.hamad27.mobileandroidjavaproject.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private TaskAdapter taskAdapter;
    private CourseAdapter courseAdapter;
    private ExamAdapter examAdapter;
    private LectureAdapter lectureAdapter;

    private boolean isTheLayoutLinear;
    private int lastClickedButton;

    /* this is to test adapters - I will add the database later */
    List<Task> tasks;
    Task task;

    List<Course> courses;
    Course course;

    List<Exam> exams;
    Exam exam;

    List<Lecture> lectures;
    Lecture lecture;
    /* this is to test adapters - I will add the database later */

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.homeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        isTheLayoutLinear = true;

        // this is just to see the adapters working
        setFakeData();

        // create all adapters
        taskAdapter = new TaskAdapter(tasks, getContext());
        courseAdapter = new CourseAdapter(courses, getContext());
        examAdapter = new ExamAdapter(exams, getContext());
        lectureAdapter = new LectureAdapter(lectures, getContext());

        binding.homeRecyclerView.setAdapter(taskAdapter);

        // at first the task button will be clicked
        binding.btnTasks.setBackgroundColor(getResources().getColor(R.color.white));
        binding.btnTasks.setTextColor(getResources().getColor(R.color.primary));
        binding.btnTasks.setTypeface(null, Typeface.BOLD);
        lastClickedButton = 1;


        binding.btnCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isTheLayoutLinear){
                    binding.homeRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                    isTheLayoutLinear = false;
                }
                binding.homeRecyclerView.setAdapter(courseAdapter);
                switchColors(2);
            }
        });
        binding.btnExams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isTheLayoutLinear){
                    binding.homeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    isTheLayoutLinear = true;
                }
                binding.homeRecyclerView.setAdapter(examAdapter);
                switchColors(4);
            }
        });
        binding.btnLectures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isTheLayoutLinear){
                    binding.homeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    isTheLayoutLinear = true;
                }
                binding.homeRecyclerView.setAdapter(lectureAdapter);
                switchColors(3);
            }
        });
        binding.btnTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isTheLayoutLinear){
                    binding.homeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    isTheLayoutLinear = true;
                }
                binding.homeRecyclerView.setAdapter(taskAdapter);
                switchColors(1);
            }
        });


    }

    private void switchColors(int buttonClicked){
        if(buttonClicked != lastClickedButton){
            // to switch the clicked button's colors
            switch (buttonClicked) {
                case 1: {
                    binding.btnTasks.setBackgroundColor(getResources().getColor(R.color.white));
                    binding.btnTasks.setTextColor(getResources().getColor(R.color.primary));
                    binding.btnTasks.setTypeface(null, Typeface.BOLD);
                    break;
                }
                case 2: {
                    binding.btnCourses.setBackgroundColor(getResources().getColor(R.color.white));
                    binding.btnCourses.setTextColor(getResources().getColor(R.color.primary));
                    binding.btnCourses.setTypeface(null, Typeface.BOLD);
                    break;
                }
                case 3: {
                    binding.btnLectures.setBackgroundColor(getResources().getColor(R.color.white));
                    binding.btnLectures.setTextColor(getResources().getColor(R.color.primary));
                    binding.btnLectures.setTypeface(null, Typeface.BOLD);
                    break;
                }
                case 4: {
                    binding.btnExams.setBackgroundColor(getResources().getColor(R.color.white));
                    binding.btnExams.setTextColor(getResources().getColor(R.color.primary));
                    binding.btnExams.setTypeface(null, Typeface.BOLD);
                    break;
                }
            }

            // to switch the previous clicked button's colors
            switch (lastClickedButton){
                case 1:{
                    binding.btnTasks.setBackgroundColor(getResources().getColor(R.color.primary));
                    binding.btnTasks.setTextColor(getResources().getColor(R.color.white));
                    binding.btnTasks.setTypeface(null, Typeface.NORMAL);
                    break;
                }
                case 2:{
                    binding.btnCourses.setBackgroundColor(getResources().getColor(R.color.primary));
                    binding.btnCourses.setTextColor(getResources().getColor(R.color.white));
                    binding.btnCourses.setTypeface(null, Typeface.NORMAL);
                    break;
                }
                case 3:{
                    binding.btnLectures.setBackgroundColor(getResources().getColor(R.color.primary));
                    binding.btnLectures.setTextColor(getResources().getColor(R.color.white));
                    binding.btnLectures.setTypeface(null, Typeface.NORMAL);
                    break;
                }
                case 4:{
                    binding.btnExams.setBackgroundColor(getResources().getColor(R.color.primary));
                    binding.btnExams.setTextColor(getResources().getColor(R.color.white));
                    binding.btnExams.setTypeface(null, Typeface.NORMAL);
                    break;
                }
            }

            lastClickedButton = buttonClicked;
        }

    }

    private void setFakeData(){
        tasks = new ArrayList<Task>();
        for(int i = 0; i<20; i++){
            task = new Task();
            task.title = "finish the home section";
            task.description = "each tab should desplay a new recycler view";
            task.course = "I3350";
            task.date = "23-12-2025";
            task.duration = 30;
            task.startingTime = "12:10 PM";
            task.type = "reading/searching";
            tasks.add(task);
        }

        courses = new ArrayList<Course>();
        for(int i = 0; i<20; i++){
            course = new Course();
            course.title = "I3350";
            course.description = "This course is very long. It needs a lot of study. But it is very important";
            courses.add(course);
        }

        lectures = new ArrayList<Lecture>();
        for(int i = 0; i<20; i++){
            lecture = new Lecture();
            lecture.lectureName = "Database room persistance";
            lecture.buildingName = "Olom";
            lecture.roomNumber = "A1";
            lecture.teacher = "Alaa Aldin";
            lecture.date = "23-12-2025";
            lecture.course = "I3350";
            lecture.startingTime = "9:55 AM";
            lecture.endingTime = "11:35 AM";
            lecture.type = "In-Person";
            lectures.add(lecture);
        }

        exams = new ArrayList<Exam>();
        for(int i = 0; i<20; i++){
            exam = new Exam();
            exam.course = "I3305";
            exam.examName = "Final";
            exam.date = "23-12-2025";
            exam.roomNumber = "D1";
            exam.duration = 90;
            exam.seatNumber = "25";
            exam.startingTime = "12:00 PM";
            exams.add(exam);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}