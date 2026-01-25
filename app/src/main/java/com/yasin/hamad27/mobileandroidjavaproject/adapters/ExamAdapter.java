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
import com.yasin.hamad27.mobileandroidjavaproject.database.Exam;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ViewHolder> {

    List<Exam> exams;
    Context context;

    public ExamAdapter(List<Exam> exams, Context context) {
        this.exams = exams;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView textView_exam_name, textView_exam_course, textView_exam_room_number,
                textView_exam_seat_number, textView_exam_duration, textView_exam_date,
                textView_exam_starting_time;

        public final ImageButton button_exam_done, button_exam_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView_exam_name = itemView.findViewById(R.id.textView_exam_name);
            textView_exam_course = itemView.findViewById(R.id.textView_exam_course);
            textView_exam_room_number = itemView.findViewById(R.id.textView_exam_room_number);
            textView_exam_duration = itemView.findViewById(R.id.textView_exam_duration);
            textView_exam_seat_number = itemView.findViewById(R.id.textView_exam_seat_number);
            textView_exam_date = itemView.findViewById(R.id.textView_exam_date);
            textView_exam_starting_time = itemView.findViewById(R.id.textView_exam_starting_time);

            button_exam_done = itemView.findViewById(R.id.button_exam_done);
            button_exam_delete = itemView.findViewById(R.id.button_exam_delete);

            button_exam_done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ExecutorService executor = Executors.newSingleThreadExecutor();
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            int index = getBindingAdapterPosition();
                            Exam exam = exams.get(index);
                            exam.isDone = true;

                            MainActivity.db.examDao().update(exam);
                        }
                    });
                }
            });
            button_exam_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ExecutorService executor = Executors.newSingleThreadExecutor();
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            int index = getBindingAdapterPosition();
                            Exam exam = exams.get(index);
                            exam.isDone = true;

                            MainActivity.db.examDao().delete(exam);
                        }
                    });
                }
            });

        }
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_exam, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamAdapter.ViewHolder holder, int position) {
        holder.textView_exam_name.setText(exams.get(position).examName);
        holder.textView_exam_course.setText(exams.get(position).course);
        holder.textView_exam_room_number.setText(exams.get(position).roomNumber);
        holder.textView_exam_seat_number.setText(exams.get(position).seatNumber);
        holder.textView_exam_date.setText(exams.get(position).date);
        holder.textView_exam_starting_time.setText(exams.get(position).startingTime);
        holder.textView_exam_duration.setText(String.valueOf(exams.get(position).duration));
    }

    @Override
    public int getItemCount() {
        return exams.size();
    }
}
