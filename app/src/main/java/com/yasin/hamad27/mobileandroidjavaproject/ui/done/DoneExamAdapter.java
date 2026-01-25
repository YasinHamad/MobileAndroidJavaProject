package com.yasin.hamad27.mobileandroidjavaproject.ui.done;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yasin.hamad27.mobileandroidjavaproject.R;

public class DoneExamAdapter extends RecyclerView.Adapter<DoneExamAdapter.MyViewHolder> {

        String[] examName;
        String[] examDate;
        int[] BtnExamDelete;

        public DoneExamAdapter(String[] examName, String[] examDate, int[] BtnExamDelete) {
            this.examName = examName;
            this.examDate = examDate;
            this.BtnExamDelete = BtnExamDelete;
        }

        public static class MyViewHolder extends RecyclerView.ViewHolder {
            private final TextView examName;
            private final TextView examDate;
            private final ImageView BtnExamDelete;

            public MyViewHolder(@NonNull View view) {
                super(view);
                examName = view.findViewById(R.id.doneTvExamName);
                examDate = view.findViewById(R.id.doneTvExamDate);
                BtnExamDelete = view.findViewById(R.id.doneBtnExamDelete);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }

            public TextView getExamName() {
                return examName;
            }

            public TextView getExamDate() {
                return examDate;
            }

            public ImageView getExamBtnDelete() {
                return BtnExamDelete;
            }
        }


        @NonNull
        @Override
        public DoneExamAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_done_exam, parent, false);
            return new DoneExamAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DoneExamAdapter.MyViewHolder holder, int position) {
            holder.getExamName().setText(examName[position]);
            holder.getExamDate().setText(examDate[position]);
            holder.getExamBtnDelete().setImageResource(BtnExamDelete[position]);
        }

        @Override
        public int getItemCount() {
            return examName.length;
        }
}
