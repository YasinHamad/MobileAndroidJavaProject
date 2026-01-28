package com.yasin.hamad27.mobileandroidjavaproject.ui.home;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.yasin.hamad27.mobileandroidjavaproject.MainActivity;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

        optimizeButtonWidths();

        // at first the task button will be clicked
        binding.btnTasks.setBackgroundColor(getResources().getColor(R.color.white));
        binding.btnTasks.setTextColor(getResources().getColor(R.color.primary));
        binding.btnTasks.setTypeface(null, Typeface.BOLD);
        lastClickedButton = 1;

        setObserversForAllTables();

        setButtonListeners();

        // click the task button when loading the home fragment
        // inorder to retrive the tasks from the db
        // then the user can click the button they want
        binding.btnTasks.performClick();

        /**AddCourse();
        AddExam();
        AddTask();
        AddLecture();**/
    }

    /**void setObserversForAllTables(){
        MainActivity.db.taskDao().getAll(false).observe((MainActivity)getContext(), new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                taskAdapter = new TaskAdapter(tasks, (MainActivity)getContext());
                binding.btnTasks.performClick();
            }
        });

        MainActivity.db.examDao().getAll(false).observe((MainActivity)getContext(), new Observer<List<Exam>>() {
            @Override
            public void onChanged(List<Exam> exams) {
                examAdapter = new ExamAdapter(exams, (MainActivity)getContext());
                binding.btnExams.performClick();
            }
        });

        MainActivity.db.lectureDao().getAll(false).observe((MainActivity)getContext(), new Observer<List<Lecture>>() {
            @Override
            public void onChanged(List<Lecture> lectures) {
                lectureAdapter = new LectureAdapter(lectures, (MainActivity)getContext());
                binding.btnLectures.performClick();
            }
        });

        MainActivity.db.courseDao().getAll(false).observe((MainActivity)getContext(), new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                courseAdapter = new CourseAdapter(courses , (MainActivity)getContext());
                binding.btnCourses.performClick();
            }
        });
    }
**/
    void setObserversForAllTables(){
        // Tasks Observer
        MainActivity.db.taskDao().getAll(false).observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                if (tasks != null && binding != null) { // ← ADD THIS CHECK
                    taskAdapter = new TaskAdapter(tasks, getContext());
                    if (lastClickedButton == 1) {
                        binding.homeRecyclerView.setAdapter(taskAdapter);
                    }
                }
            }
        });

        // Courses Observer
        MainActivity.db.courseDao().getAll(false).observe(getViewLifecycleOwner(), new Observer<List<Course>>() {
            @Override
            public void onChanged(List<Course> courses) {
                if (courses != null && binding != null) { // ← ADD THIS CHECK
                    courseAdapter = new CourseAdapter(courses, getContext());
                    if (lastClickedButton == 2) {
                        binding.homeRecyclerView.setAdapter(courseAdapter);
                    }
                }
            }
        });

        // Lectures Observer
        MainActivity.db.lectureDao().getAll(false).observe(getViewLifecycleOwner(), new Observer<List<Lecture>>() {
            @Override
            public void onChanged(List<Lecture> lectures) {
                if (lectures != null && binding != null) { // ← ADD THIS CHECK
                    lectureAdapter = new LectureAdapter(lectures, getContext());
                    if (lastClickedButton == 3) {
                        binding.homeRecyclerView.setAdapter(lectureAdapter);
                    }
                }
            }
        });

        // Exams Observer
        MainActivity.db.examDao().getAll(false).observe(getViewLifecycleOwner(), new Observer<List<Exam>>() {
            @Override
            public void onChanged(List<Exam> exams) {
                if (exams != null && binding != null) { // ← ADD THIS CHECK
                    examAdapter = new ExamAdapter(exams, getContext());
                    if (lastClickedButton == 4) {
                        binding.homeRecyclerView.setAdapter(examAdapter);
                    }
                }
            }
        });
    }
    void setButtonListeners(){
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

    // since the buttons (tasks-lectures-exams-courses) are fixed
    // there will be extra free space on the right
    // this function takes that extra free space, divides by 4, and adds it to the button widths
    private void optimizeButtonWidths() {
        DisplayMetrics display = getResources().getDisplayMetrics();
        int width = display.widthPixels;

        int px_to_remove = Math.round((float) 46 * display.density);
        int buttons_size = binding.btnTasks.getMaxWidth() + binding.btnCourses.getMaxWidth() + binding.btnExams.getMaxWidth() + binding.btnLectures.getMaxWidth();
        int free_space = width - (px_to_remove + buttons_size);

        int width_to_add = free_space / 4;

        binding.btnTasks.setWidth(width_to_add + binding.btnTasks.getMaxWidth());
        binding.btnCourses.setWidth(width_to_add + binding.btnCourses.getMaxWidth());
        binding.btnExams.setWidth(width_to_add + binding.btnExams.getMaxWidth());
        binding.btnLectures.setWidth(width_to_add + binding.btnLectures.getMaxWidth());
    }
}

    // the following 6 functions were for testing
