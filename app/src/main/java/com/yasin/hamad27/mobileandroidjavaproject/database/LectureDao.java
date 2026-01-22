package com.yasin.hamad27.mobileandroidjavaproject.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LectureDao {
    @Query("SELECT * FROM LECTURE WHERE isDone =:isDone")
    LiveData<List<Lecture>> getAll(boolean isDone);

    @Query("SELECT * from LECTURE WHERE id =:id")
    Lecture getLectureById(int id);

    @Insert
    void insertAll(Lecture... lectures);

    @Insert
    void insert(Lecture lecture);

    @Delete
    void deleteAll(Lecture... lectures);

    @Delete
    void delete(Lecture lecture);

    @Update
    void update(Lecture lecture);
}
