package com.yasin.hamad27.mobileandroidjavaproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yasin.hamad27.mobileandroidjavaproject.R;
import com.yasin.hamad27.mobileandroidjavaproject.database.Lecture;

import java.util.List;

public class LectureAdapter extends RecyclerView.Adapter<LectureAdapter.ViewHolder> {

    List<Lecture> lectures;
    Context context;

    public LectureAdapter(List<Lecture> lectures, Context context) {
        this.lectures = lectures;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView textView_lecture_title, textView_lecture_building_name, textView_lecture_room_number,
                textView_lecture_type, textView_lecture_date, textView_lecture_starting_time,
                textView_lecture_ending_time, textView_lecture_course, textView_lecture_teacher;

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

        }
    }


    @Override
    public LectureAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_lecture, parent, false);
        return new LectureAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView_lecture_title.setText(lectures.get(position).lectureName);
        holder.textView_lecture_building_name.setText(lectures.get(position).buildingName);
        holder.textView_lecture_room_number.setText(lectures.get(position).roomNumber);
        holder.textView_lecture_type.setText(lectures.get(position).type);
        holder.textView_lecture_date.setText(lectures.get(position).date);
        holder.textView_lecture_starting_time.setText(lectures.get(position).startingTime);
        holder.textView_lecture_ending_time.setText(lectures.get(position).endingTime);
        holder.textView_lecture_course.setText(lectures.get(position).course);
        holder.textView_lecture_teacher.setText(lectures.get(position).teacher);
    }

    @Override
    public int getItemCount() {
        return lectures.size();
    }
}