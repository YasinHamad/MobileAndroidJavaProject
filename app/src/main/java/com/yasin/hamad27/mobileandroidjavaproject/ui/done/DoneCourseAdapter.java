package com.yasin.hamad27.mobileandroidjavaproject.ui.done;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yasin.hamad27.mobileandroidjavaproject.MainActivity;
import com.yasin.hamad27.mobileandroidjavaproject.R;
import com.yasin.hamad27.mobileandroidjavaproject.database.Course;
import com.yasin.hamad27.mobileandroidjavaproject.database.Lecture;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DoneCourseAdapter extends RecyclerView.Adapter<DoneCourseAdapter.MyViewHolder> {

    private List<Course> courseList;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();


    public DoneCourseAdapter(List<Course> courseList) {
        this.courseList = courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView courseTitle;
        ImageView btnCourseDelete;

        public MyViewHolder(@NonNull View view) {
            super(view);
            courseTitle = view.findViewById(R.id.doneTvCourseTitle);
            btnCourseDelete = view.findViewById(R.id.doneBtnCourseDelete);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType
    ) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_done_course, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull MyViewHolder holder, int position
    ) {
        Course course = courseList.get(position);

        holder.courseTitle.setText(course.title);

        holder.btnCourseDelete.setOnClickListener(v -> {
            int index = holder.getBindingAdapterPosition();
            if (index != RecyclerView.NO_POSITION) { // always check
                Course courseToDelete = courseList.get(index);
                executor.execute(() -> {
                    MainActivity.db.courseDao().delete(courseToDelete);
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseList == null ? 0 : courseList.size();
    }
}
