package com.yasin.hamad27.mobileandroidjavaproject.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Course.class, Exam.class, Lecture.class, Task.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CourseDao courseDao();
    public abstract TaskDao taskDao();
    public abstract ExamDao examDao();
    public abstract LectureDao lectureDao();
}
