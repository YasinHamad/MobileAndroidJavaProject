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

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DoneCourseAdapter extends RecyclerView.Adapter<DoneCourseAdapter.MyViewHolder> {

    private List<Course> courseList;

    public DoneCourseAdapter(List<Course> courseList) {
        this.courseList = courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView courseName;
        ImageView btnDelete;

        public MyViewHolder(@NonNull View view) {
            super(view);
            courseName = view.findViewById(R.id.doneTvCourseName);
            btnDelete = view.findViewById(R.id.doneBtnCourseDelete);
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

        holder.courseName.setText(course.title);

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExecutorService executor = Executors.newSingleThreadExecutor();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        MainActivity.db.courseDao().delete(course);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }
}
