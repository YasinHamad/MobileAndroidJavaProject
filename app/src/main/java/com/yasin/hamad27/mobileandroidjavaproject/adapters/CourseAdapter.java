package com.yasin.hamad27.mobileandroidjavaproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yasin.hamad27.mobileandroidjavaproject.MainActivity;
import com.yasin.hamad27.mobileandroidjavaproject.R;
import com.yasin.hamad27.mobileandroidjavaproject.database.Course;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    List<Course> courses;
    Context context;

    public CourseAdapter(List<Course> courses, Context context) {
        this.courses = courses;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView textView_course_title, textView_course_description;

        public final ImageButton button_course_done, button_course_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView_course_title = itemView.findViewById(R.id.textView_course_title);
            textView_course_description = itemView.findViewById(R.id.textView_course_description);

            button_course_done = itemView.findViewById(R.id.button_course_done);
            button_course_delete = itemView.findViewById(R.id.button_course_delete);

            button_course_done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ExecutorService executor = Executors.newSingleThreadExecutor();
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            int index = getBindingAdapterPosition();
                            Course course = courses.get(index);
                            course.isDone = true;

                            MainActivity.db.courseDao().update(course);
                        }
                    });
                }
            });

            button_course_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ExecutorService executor = Executors.newSingleThreadExecutor();
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            int index = getBindingAdapterPosition();
                            Course course = courses.get(index);
                            course.isDone = true;

                            MainActivity.db.courseDao().delete(course);
                        }
                    });
                }
            });



        }
    }


    @Override
    public CourseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_course, parent, false);
        return new CourseAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.ViewHolder holder, int position) {
        holder.textView_course_title.setText(courses.get(position).title);
        holder.textView_course_description.setText(courses.get(position).description);

    }

    @Override
    public int getItemCount() {
        return courses.size();
    }
}
