package com.yasin.hamad27.mobileandroidjavaproject.ui.done;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yasin.hamad27.mobileandroidjavaproject.R;

public class DoneCourseAdapter extends RecyclerView.Adapter<DoneCourseAdapter.MyViewHolder> {

        String[] courseTitle;
        String[] courseDescription;
        int[] BtnCourseDelete;

        public DoneCourseAdapter(String[] courseTitle, String[] courseDescription, int[] BtnCourseDelete) {
            this.courseTitle = courseTitle;
            this.courseDescription = courseDescription;
            this.BtnCourseDelete = BtnCourseDelete;
        }

        public static class MyViewHolder extends RecyclerView.ViewHolder {
            private final TextView courseTitle;
            private final TextView courseDescription;
            private final ImageView BtnCourseDelete;

            public MyViewHolder(@NonNull View view) {
                super(view);
                courseTitle = view.findViewById(R.id.doneTvCourseTitle);
                courseDescription = view.findViewById(R.id.doneTvCourseDescription);
                BtnCourseDelete = view.findViewById(R.id.doneBtnCourseDelete);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }

            public TextView getCourseTitle() {
                return courseTitle;
            }

            public TextView getCourseDescription() {
                return courseDescription;
            }

            public ImageView getCourseBtnDelete() {
                return BtnCourseDelete;
            }
        }


        @NonNull
        @Override
        public DoneCourseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_done_lecture, parent, false);
            return new DoneCourseAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DoneCourseAdapter.MyViewHolder holder, int position) {
            holder.getCourseTitle().setText(courseTitle[position]);
            holder.getCourseDescription().setText(courseDescription[position]);
            holder.getCourseBtnDelete().setImageResource(BtnCourseDelete[position]);
        }

        @Override
        public int getItemCount() {
            return courseTitle.length;
        }
}
