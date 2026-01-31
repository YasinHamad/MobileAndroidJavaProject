package com.yasin.hamad27.mobileandroidjavaproject.ui.done;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yasin.hamad27.mobileandroidjavaproject.MainActivity;
import com.yasin.hamad27.mobileandroidjavaproject.R;
import com.yasin.hamad27.mobileandroidjavaproject.database.Task;
import com.yasin.hamad27.mobileandroidjavaproject.databinding.FragmentDoneBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DoneFragment extends Fragment {

    private FragmentDoneBinding binding;
    String currentSection;

    private RecyclerView recyclerView;
    private DoneTaskAdapter taskAdapter;
    private DoneCourseAdapter courseAdapter;
    private DoneLectureAdapter lectureAdapter;
    private DoneExamAdapter examAdapter;
    private ExecutorService executor;

    private int lastClickedButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentDoneBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        // 🔹 Setup RecyclerView
        recyclerView = binding.doneRecView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        taskAdapter = new DoneTaskAdapter(new ArrayList<>()); // start with empty list
        courseAdapter = new DoneCourseAdapter(new ArrayList<>());
        lectureAdapter = new DoneLectureAdapter(new ArrayList<>());
        examAdapter = new DoneExamAdapter(new ArrayList<>());
        recyclerView.setAdapter(taskAdapter);

        executor = Executors.newSingleThreadExecutor();

        currentSection = "tasks";
        loadDataFromDb(currentSection);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        optimizeButtonWidths();

        currentSection = "tasks";


        binding.btnTasks.setBackgroundColor(getResources().getColor(R.color.white));
        binding.btnTasks.setTextColor(getResources().getColor(R.color.primary));
        binding.btnTasks.setTypeface(null, Typeface.BOLD);
        lastClickedButton = 1;

        binding.btnTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSection = "tasks";
                recyclerView.setAdapter(taskAdapter);
                switchColors(1);
                loadDataFromDb("tasks");
            }
        });

        binding.btnCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSection = "courses";
                recyclerView.setAdapter(courseAdapter);
                switchColors(2);
                loadDataFromDb("courses");
            }
        });

        binding.btnLectures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSection = "lectures";
                loadDataFromDb("lectures");
                switchColors(3);
                recyclerView.setAdapter(lectureAdapter);
            }
        });

        binding.btnExams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSection = "exams";
                recyclerView.setAdapter(examAdapter);
                switchColors(4);
                loadDataFromDb("exams");
            }
        });

        binding.doneBtnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Delete All")
                        .setMessage("Are you sure you want to delete all " + currentSection + "?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                ExecutorService executor = Executors.newSingleThreadExecutor();
                                executor.execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        switch (currentSection) {
                                            case "tasks":
                                                MainActivity.db.taskDao().deleteAllDoneTasks();
                                                break;
                                            case "courses":
                                                MainActivity.db.courseDao().deleteAllDoneCourses();
                                                break;
                                            case "lectures":
                                                MainActivity.db.lectureDao().deleteAllDoneLectures();
                                                break;
                                            case "exams":
                                                MainActivity.db.examDao().deleteAllDoneExams();
                                                break;
                                        }
                                    }

                                });
                            }
                        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
            }
        });
    }



    private void loadDataFromDb(String section) {
        switch (section) {
            case "tasks":
                MainActivity.db.taskDao().getAll(true).observe(getViewLifecycleOwner(), tasks -> {
                    taskAdapter.setTaskList(tasks);
                });
                break;

            case "courses":
                MainActivity.db.courseDao().getAll(true).observe(getViewLifecycleOwner(), courses -> {
                    courseAdapter.setCourseList(courses);
                });
                break;
            case "lectures":
                MainActivity.db.lectureDao().getAll(true).observe(getViewLifecycleOwner(), lectures -> {
                    lectureAdapter.setLectureList(lectures);
                });
                // TODO: observe Lecture LiveData
                break;
            case "exams":
                MainActivity.db.examDao().getAll(true).observe(getViewLifecycleOwner(), exams -> {
                    examAdapter.setExamList(exams);
                });
                // TODO: observe Exam LiveData
                break;
        }
    }



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
    private void switchColors(int buttonClicked){
        if(buttonClicked != lastClickedButton){

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

}