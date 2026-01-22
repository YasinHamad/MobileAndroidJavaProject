package com.yasin.hamad27.mobileandroidjavaproject.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Lecture {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String type;
    public String lectureName;
    public String roomNumber;
    public String buildingName;
    public String teacher;
    public String course;
    public String date;
    public String startingTime;
    public String endingTime;
    public boolean isDone = false;
}
