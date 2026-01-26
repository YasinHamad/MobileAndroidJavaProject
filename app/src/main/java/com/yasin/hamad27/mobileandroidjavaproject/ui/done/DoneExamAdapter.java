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

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DoneExamAdapter extends RecyclerView.Adapter<DoneExamAdapter.MyViewHolder> {

    private List<Exam> examList;

    public DoneExamAdapter(List<Exam> examList) {
        this.examList = examList;
    }

    public void setExamList(List<Exam> examList) {
        this.examList = examList;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView examName, examDate;
        ImageView btnDelete;

        public MyViewHolder(@NonNull View view) {
            super(view);
            examName = view.findViewById(R.id.doneTvExamName);
            examDate = view.findViewById(R.id.doneTvExamDate);
            btnDelete = view.findViewById(R.id.doneBtnExamDelete);
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

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExecutorService executor = Executors.newSingleThreadExecutor();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        MainActivity.db.examDao().delete(exam);

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return examList.size();
    }
}
