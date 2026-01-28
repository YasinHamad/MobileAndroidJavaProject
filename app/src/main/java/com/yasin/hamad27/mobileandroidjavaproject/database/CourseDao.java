package com.yasin.hamad27.mobileandroidjavaproject.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CourseDao {
    @Query("SELECT * FROM COURSE WHERE isDone =:isDone")
    LiveData<List<Course>> getAll(boolean isDone);

    @Query("SELECT * from COURSE WHERE id =:id")
    Course getCourseById(int id);

    @Insert
    void insertAll(Course... courses);

    @Insert
    void insert(Course course);

    @Delete
    void deleteAll(Course... courses);

    @Delete
    void delete(Course course);

    @Query("DELETE FROM COURSE WHERE isDone = 1")
    void deleteAllDoneCourses();

    @Update
    void update(Course course);


}
