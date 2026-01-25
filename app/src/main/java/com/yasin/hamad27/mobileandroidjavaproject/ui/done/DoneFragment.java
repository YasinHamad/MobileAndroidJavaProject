package com.yasin.hamad27.mobileandroidjavaproject.ui.done;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yasin.hamad27.mobileandroidjavaproject.MainActivity;
import com.yasin.hamad27.mobileandroidjavaproject.R;
import com.yasin.hamad27.mobileandroidjavaproject.databinding.FragmentDoneBinding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DoneFragment extends Fragment {

    private FragmentDoneBinding binding;
    String currentSection;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentDoneBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        optimizeButtonWidths();

        currentSection = "tasks";

        binding.btnTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSection = "tasks";
            }
        });

        binding.btnCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSection = "courses";
            }
        });

        binding.btnLectures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSection = "lectures";
            }
        });

        binding.btnExams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSection = "exams";
            }
        });

        binding.doneBtnDeleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExecutorService executor = Executors.newSingleThreadExecutor();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        switch (currentSection) {
                            case "tasks":
                                MainActivity.db.taskDao().deleteAll();
                                break;
                            case "courses":
                                MainActivity.db.courseDao().deleteAll();
                                break;
                            case "lectures":
                                MainActivity.db.lectureDao().deleteAll();
                                break;
                            case "exams":
                                MainActivity.db.examDao().deleteAll();
                                break;
                        }
                    }
                });
            }
        });
    }

    // since the buttons (tasks-lectures-exams-courses) are fixed
    // there will be extra free space on the right
    // this function takes that extra free space, divides by 4, and adds it to the button widths
    private void optimizeButtonWidths(){
        DisplayMetrics display = getResources().getDisplayMetrics();
        int width = display.widthPixels;

        int px_to_remove = Math.round((float) 46 * display.density);
        int buttons_size = binding.btnTasks.getMaxWidth() + binding.btnCourses.getMaxWidth() + binding.btnExams.getMaxWidth() + binding.btnLectures.getMaxWidth();
        int free_space = width - (px_to_remove + buttons_size);

        int width_to_add = free_space/4;

        binding.btnTasks.setWidth(width_to_add + binding.btnTasks.getMaxWidth());
        binding.btnCourses.setWidth(width_to_add +binding.btnCourses.getMaxWidth());
        binding.btnExams.setWidth(width_to_add + binding.btnExams.getMaxWidth());
        binding.btnLectures.setWidth(width_to_add + binding.btnLectures.getMaxWidth());
    }
}