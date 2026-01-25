package com.yasin.hamad27.mobileandroidjavaproject.ui.done;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yasin.hamad27.mobileandroidjavaproject.R;

public class DoneTaskAdapter extends RecyclerView.Adapter<DoneTaskAdapter.MyViewHolder> {

    String[] taskTitle;
    String[] taskDescription;
    int[] BtnTaskDelete;

    public DoneTaskAdapter(String[] taskTitle, String[] taskDescription, int[] BtnTaskDelete) {
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
        this.BtnTaskDelete = BtnTaskDelete;
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
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
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
        holder.getTaskTitle().setText(taskTitle[position]);
        holder.getTaskDescription().setText(taskDescription[position]);
        holder.getTaskBtnDelete().setImageResource(BtnTaskDelete[position]);
    }

    @Override
    public int getItemCount() {
        return taskTitle.length;
    }
}
