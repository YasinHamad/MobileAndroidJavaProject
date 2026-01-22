package com.yasin.hamad27.mobileandroidjavaproject.ui.add;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yasin.hamad27.mobileandroidjavaproject.R;
import com.yasin.hamad27.mobileandroidjavaproject.databinding.FragmentAddBinding;
import com.yasin.hamad27.mobileandroidjavaproject.databinding.FragmentHomeBinding;

public class AddFragment extends Fragment {

    private FragmentAddBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentAddBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        optimizeButtonWidths();

        binding.btnTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),AddTaskActivity.class);
                startActivity(i);
            }
        });
        binding.btnLectures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),AddLectureActivity.class);
                startActivity(i);
            }
        });
        binding.btnExams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),AddExamActivity.class);
                startActivity(i);
            }
        });
        binding.btnCourses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),AddCourseActivity.class);
                startActivity(i);
            }
        });
    }

    // since the buttons (tasks-lectures-exams-courses) are fixed
    // there will be extra free space on the right
    // this function takes that extra free space, divides by 2, and adds it to the button widths
    private void optimizeButtonWidths(){
        DisplayMetrics display = getResources().getDisplayMetrics();
        int width = display.widthPixels;

        int px_to_remove = Math.round((float) 30 * display.density);
        int buttons_size = binding.btnTasks.getWidth() + binding.btnCourses.getWidth();
        int free_space = width - (px_to_remove + buttons_size);

        int width_to_add = free_space/2;

        binding.btnTasks.setWidth(width_to_add + binding.btnTasks.getWidth());
        binding.btnCourses.setWidth(width_to_add +binding.btnCourses.getWidth());
        binding.btnExams.setWidth(width_to_add + binding.btnExams.getWidth());
        binding.btnLectures.setWidth(width_to_add + binding.btnLectures.getWidth());

        binding.btnTasks.setHeight(width_to_add + binding.btnTasks.getWidth());
        binding.btnCourses.setHeight(width_to_add +binding.btnCourses.getWidth());
        binding.btnExams.setHeight(width_to_add + binding.btnExams.getWidth());
        binding.btnLectures.setHeight(width_to_add + binding.btnLectures.getWidth());
    }
}