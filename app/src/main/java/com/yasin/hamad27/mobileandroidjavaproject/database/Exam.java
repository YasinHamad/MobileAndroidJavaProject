package com.yasin.hamad27.mobileandroidjavaproject.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Exam {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String examName;
    public String course;
    public String seatNumber;
    public String roomNumber;
    public String date;
    public String startingTime;
    public int duration;
    public boolean isDone = false;
}
