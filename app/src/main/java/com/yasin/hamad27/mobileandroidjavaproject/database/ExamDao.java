package com.yasin.hamad27.mobileandroidjavaproject.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ExamDao {
    @Query("SELECT * FROM EXAM WHERE isDone =:isDone")
    LiveData<List<Exam>> getAll(boolean isDone);

    @Query("SELECT * from EXAM WHERE id =:id")
    Exam getExamById(int id);

    @Insert
    void insertAll(Exam... exams);

    @Insert
    void insert(Exam exam);

    @Delete
    void deleteAll(Exam... exams);

    @Query("DELETE FROM EXAM WHERE isDone = 1")
    void deleteAllDoneExams();

    @Delete
    void delete(Exam exam);

    @Update
    void update(Exam exam);
}
