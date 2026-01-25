package com.yasin.hamad27.mobileandroidjavaproject.adapters;

import static android.view.View.INVISIBLE;

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
import com.yasin.hamad27.mobileandroidjavaproject.database.Lecture;
import com.yasin.hamad27.mobileandroidjavaproject.database.Task;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    List<Task> tasks;
    Context context;

    public TaskAdapter(List<Task> tasks, Context context) {
        this.tasks = tasks;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView textView_task_title, textView_task_description, textView_task_course,
                textView_task_date, textView_task_startingTime, textView_task_duration,
                textView_task_type;

        public final ImageButton button_task_done, button_task_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView_task_title = itemView.findViewById(R.id.textView_task_title);
            textView_task_description = itemView.findViewById(R.id.textView_task_description);
            textView_task_course = itemView.findViewById(R.id.textView_task_course);
            textView_task_date = itemView.findViewById(R.id.textView_task_date);
            textView_task_duration = itemView.findViewById(R.id.textView_task_duration);
            textView_task_startingTime = itemView.findViewById(R.id.textView_task_startingTime);
            textView_task_type = itemView.findViewById(R.id.textView_task_type);

            button_task_done = itemView.findViewById(R.id.button_task_done);
            button_task_delete = itemView.findViewById(R.id.button_task_delete);

            button_task_done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ExecutorService executor = Executors.newSingleThreadExecutor();
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            int index = getBindingAdapterPosition();
                            Task task = tasks.get(index);
                            task.isDone = true;

                            MainActivity.db.taskDao().update(task);
                        }
                    });
                }
            });
            button_task_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ExecutorService executor = Executors.newSingleThreadExecutor();
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            int index = getBindingAdapterPosition();
                            Task task = tasks.get(index);
                            task.isDone = true;

                            MainActivity.db.taskDao().delete(task);
                        }
                    });
                }
            });
        }
    }


    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        holder.textView_task_title.setText(tasks.get(position).title);
        holder.textView_task_description.setText(tasks.get(position).description);
        holder.textView_task_course.setText(tasks.get(position).course);
        holder.textView_task_date.setText(tasks.get(position).date);
        holder.textView_task_startingTime.setText(tasks.get(position).startingTime);
        holder.textView_task_type.setText(tasks.get(position).type);
        holder.textView_task_duration.setText(String.valueOf(tasks.get(position).duration));

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}
