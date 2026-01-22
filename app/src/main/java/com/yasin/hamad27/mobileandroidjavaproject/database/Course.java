package com.yasin.hamad27.mobileandroidjavaproject.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Course {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    public String description;
    public boolean isDone = false;
}
