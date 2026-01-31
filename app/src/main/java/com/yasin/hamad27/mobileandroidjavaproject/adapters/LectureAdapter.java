package com.yasin.hamad27.mobileandroidjavaproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yasin.hamad27.mobileandroidjavaproject.MainActivity;
import com.yasin.hamad27.mobileandroidjavaproject.R;
import com.yasin.hamad27.mobileandroidjavaproject.database.Lecture;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LectureAdapter extends RecyclerView.Adapter<LectureAdapter.ViewHolder> {

    List<Lecture> lectures;
    Context context;

    public LectureAdapter(List<Lecture> lectures, Context context) {
        this.lectures = lectures;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView textView_lecture_title, textView_lecture_building_name,
                textView_lecture_room_number, textView_lecture_type, textView_lecture_date,
                textView_lecture_starting_time, textView_lecture_ending_time,
                textView_lecture_course, textView_lecture_teacher;

        public final ImageButton button_lecture_done, button_lecture_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView_lecture_title = itemView.findViewById(R.id.textView_lecture_title);
            textView_lecture_building_name = itemView.findViewById(R.id.textView_lecture_building_name);
            textView_lecture_room_number = itemView.findViewById(R.id.textView_lecture_room_number);
            textView_lecture_type = itemView.findViewById(R.id.textView_lecture_type);
            textView_lecture_date = itemView.findViewById(R.id.textView_lecture_date);
            textView_lecture_starting_time = itemView.findViewById(R.id.textView_lecture_starting_time);
            textView_lecture_ending_time = itemView.findViewById(R.id.textView_lecture_ending_time);
            textView_lecture_course = itemView.findViewById(R.id.textView_lecture_course);
            textView_lecture_teacher = itemView.findViewById(R.id.textView_lecture_teacher);

            button_lecture_done = itemView.findViewById(R.id.button_lecture_done);
            button_lecture_delete = itemView.findViewById(R.id.button_lecture_delete);

            button_lecture_done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ExecutorService executor = Executors.newSingleThreadExecutor();
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            int index = getBindingAdapterPosition();
                            Lecture lecture = lectures.get(index);
                            lecture.isDone = true;

                            MainActivity.db.lectureDao().update(lecture);
                        }
                    });
                }
            });


            button_lecture_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ExecutorService executor = Executors.newSingleThreadExecutor();
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            int index = getBindingAdapterPosition();
                            Lecture lecture = lectures.get(index);

                            MainActivity.db.lectureDao().delete(lecture);
                        }
                    });
                }
            });
        }
    }

    @Override
    public LectureAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_lecture, parent, false);
        return new LectureAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LectureAdapter.ViewHolder holder, int position) {
        Lecture currentLecture = lectures.get(position);

        holder.textView_lecture_title.setText(currentLecture.lectureName);
        holder.textView_lecture_building_name.setText(currentLecture.buildingName);
        holder.textView_lecture_room_number.setText(currentLecture.roomNumber);
        holder.textView_lecture_type.setText(currentLecture.type);
        holder.textView_lecture_date.setText(currentLecture.date);
        holder.textView_lecture_starting_time.setText(currentLecture.startingTime);
        holder.textView_lecture_ending_time.setText(currentLecture.endingTime);
        holder.textView_lecture_course.setText(currentLecture.course);
        holder.textView_lecture_teacher.setText(currentLecture.teacher);
    }

    @Override
    public int getItemCount() {
        return lectures.size();
    }
}