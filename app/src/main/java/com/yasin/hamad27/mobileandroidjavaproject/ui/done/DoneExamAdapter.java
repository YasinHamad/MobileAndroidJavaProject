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
import com.yasin.hamad27.mobileandroidjavaproject.database.Exam;
import com.yasin.hamad27.mobileandroidjavaproject.database.Lecture;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DoneExamAdapter extends RecyclerView.Adapter<DoneExamAdapter.MyViewHolder> {

    private List<Exam> examList;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public DoneExamAdapter(List<Exam> examList) {
        this.examList = examList;
    }

    public void setExamList(List<Exam> examList) {
        this.examList = examList;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView examName, examDate;
        ImageView btnExamDelete;

        public MyViewHolder(@NonNull View view) {
            super(view);
            examName = view.findViewById(R.id.doneTvExamName);
            examDate = view.findViewById(R.id.doneTvExamDate);
            btnExamDelete = view.findViewById(R.id.doneBtnExamDelete);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_done_exam, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Exam exam = examList.get(position);

        holder.examName.setText(exam.examName);
        holder.examDate.setText(exam.date);

        holder.btnExamDelete.setOnClickListener(v -> {
            int index = holder.getBindingAdapterPosition();
            if (index != RecyclerView.NO_POSITION) { // always check
                Exam examToDelete = examList.get(index);
                executor.execute(() -> {
                    MainActivity.db.examDao().delete(examToDelete);
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return examList.size();
    }
}
