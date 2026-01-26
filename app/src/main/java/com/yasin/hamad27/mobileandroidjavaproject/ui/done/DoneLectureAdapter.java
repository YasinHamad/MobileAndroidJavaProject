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
import com.yasin.hamad27.mobileandroidjavaproject.database.Task;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DoneLectureAdapter extends RecyclerView.Adapter<DoneLectureAdapter.MyViewHolder> {

    private List<Lecture> lectureList;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();


    public DoneLectureAdapter(List<Lecture> lectureList) {
        this.lectureList = lectureList;
    }

    public void setLectureList(List<Lecture> lectureList) {
        this.lectureList = lectureList;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView lectureTitle, courseName;
        ImageView btnLectureDelete;

        public MyViewHolder(@NonNull View view) {
            super(view);
            lectureTitle = view.findViewById(R.id.doneTvLectureTitle);
            courseName = view.findViewById(R.id.doneTvCourseName);
            btnLectureDelete = view.findViewById(R.id.doneBtnLectureDelete);
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

        holder.btnLectureDelete.setOnClickListener(v -> {
            int index = holder.getBindingAdapterPosition();
            if (index != RecyclerView.NO_POSITION) { // always check
                Lecture lectureToDelete = lectureList.get(index);
                executor.execute(() -> {
                    MainActivity.db.lectureDao().delete(lectureToDelete);
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return lectureList == null ? 0 : lectureList.size();
    }
}
