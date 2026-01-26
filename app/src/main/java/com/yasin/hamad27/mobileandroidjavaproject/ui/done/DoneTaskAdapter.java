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
import com.yasin.hamad27.mobileandroidjavaproject.database.Task;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DoneTaskAdapter extends RecyclerView.Adapter<DoneTaskAdapter.MyViewHolder> {

    // 🔹 CHANGED: arrays → List<Task>
    private List<Task> taskList;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    // 🔹 CHANGED: constructor
    public DoneTaskAdapter(List<Task> taskList) {
        this.taskList = taskList;
    }

    // 🔹 ADDED: allows updating data after DB fetch
    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView taskTitle;
        private final TextView taskDescription;
        private final ImageView BtnTaskDelete;

        public MyViewHolder(@NonNull View view) {
            super(view);
            taskTitle = view.findViewById(R.id.doneTvTaskTitle);
            taskDescription = view.findViewById(R.id.doneTvTaskDescription);
            BtnTaskDelete = view.findViewById(R.id.doneBtnTaskDelete);
        }

        public TextView getTaskTitle() {
            return taskTitle;
        }

        public TextView getTaskDescription() {
            return taskDescription;
        }

        public ImageView getTaskBtnDelete() {
            return BtnTaskDelete;
        }
    }

    @NonNull
    @Override
    public DoneTaskAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_done_task, parent, false);
        return new DoneTaskAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoneTaskAdapter.MyViewHolder holder, int position) {
        Task task = taskList.get(position);

        holder.getTaskTitle().setText(task.title);
        holder.getTaskDescription().setText(task.description);

        // 🔹 Safe deletion using binding adapter position
        holder.BtnTaskDelete.setOnClickListener(v -> {
            int index = holder.getBindingAdapterPosition();
            if (index != RecyclerView.NO_POSITION) { // always check
                Task taskToDelete = taskList.get(index);
                executor.execute(() -> {
                    MainActivity.db.taskDao().delete(taskToDelete);
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList == null ? 0 : taskList.size();
    }

}
