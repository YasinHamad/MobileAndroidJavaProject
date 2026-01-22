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

import com.yasin.hamad27.mobileandroidjavaproject.R;
import com.yasin.hamad27.mobileandroidjavaproject.databinding.FragmentDoneBinding;

public class DoneFragment extends Fragment {

    private FragmentDoneBinding binding;

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