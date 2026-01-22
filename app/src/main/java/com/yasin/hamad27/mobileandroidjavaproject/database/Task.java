package com.yasin.hamad27.mobileandroidjavaproject.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Task {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    public String description;
    public String course;
    public String type;
    public String date;
    public String startingTime;
    public int duration;
    public boolean isDone = false;
}
