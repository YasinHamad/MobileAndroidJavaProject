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
import com.yasin.hamad27.mobileandroidjavaproject.database.Lecture;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DoneLectureAdapter extends RecyclerView.Adapter<DoneLectureAdapter.MyViewHolder> {

    private List<Lecture> lectureList;

    public DoneLectureAdapter(List<Lecture> lectureList) {
        this.lectureList = lectureList;
    }

    public void setLectureList(List<Lecture> lectureList) {
        this.lectureList = lectureList;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView lectureTitle, courseName;
        ImageView btnDelete;

        public MyViewHolder(@NonNull View view) {
            super(view);
            lectureTitle = view.findViewById(R.id.doneTvLectureTitle);
            courseName = view.findViewById(R.id.doneTvCourseName);
            btnDelete = view.findViewById(R.id.doneBtnLectureDelete);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_done_lecture, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Lecture lecture = lectureList.get(position);

        holder.lectureTitle.setText(lecture.lectureName);
        holder.courseName.setText(lecture.course);

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExecutorService executor = Executors.newSingleThreadExecutor();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        MainActivity.db.lectureDao().delete(lecture);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return lectureList.size();
    }
}
