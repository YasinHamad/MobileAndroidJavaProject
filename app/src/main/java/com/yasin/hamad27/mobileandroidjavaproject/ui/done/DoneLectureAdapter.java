package com.yasin.hamad27.mobileandroidjavaproject.ui.done;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yasin.hamad27.mobileandroidjavaproject.R;

public class DoneLectureAdapter extends RecyclerView.Adapter<DoneLectureAdapter.MyViewHolder> {

    String[] lectureTitle;
    String[] courseName;
    int[] BtnLectureDelete;

    public DoneLectureAdapter(String[] lectureTitle, String[] courseName, int[] BtnLectureDelete) {
        this.lectureTitle = lectureTitle;
        this.courseName = courseName;
        this.BtnLectureDelete = BtnLectureDelete;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView lectureTitle;
        private final TextView courseName;
        private final ImageView BtnLectureDelete;

        public MyViewHolder(@NonNull View view) {
            super(view);
            lectureTitle = view.findViewById(R.id.doneTvLectureTitle);
            courseName = view.findViewById(R.id.doneTvCourseName);
            BtnLectureDelete = view.findViewById(R.id.doneBtnLectureDelete);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        public TextView getLectureTitle() {
            return lectureTitle;
        }

        public TextView getCourseName() {
            return courseName;
        }

        public ImageView getLectureBtnDelete() {
            return BtnLectureDelete;
        }
    }


        @NonNull
        @Override
        public DoneLectureAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_done_lecture, parent, false);
            return new DoneLectureAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DoneLectureAdapter.MyViewHolder holder, int position) {
            holder.getLectureTitle().setText(lectureTitle[position]);
            holder.getCourseName().setText(courseName[position]);
            holder.getLectureBtnDelete().setImageResource(BtnLectureDelete[position]);
        }

        @Override
        public int getItemCount() {
            return lectureTitle.length;
        }
}
