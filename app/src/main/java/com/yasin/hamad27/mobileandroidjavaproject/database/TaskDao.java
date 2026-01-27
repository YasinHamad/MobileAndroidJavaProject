package com.yasin.hamad27.mobileandroidjavaproject.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface TaskDao {
    @Query("SELECT * FROM TASK WHERE isDone =:isDone")
    LiveData<List<Task>> getAll(boolean isDone);

    @Query("SELECT * from TASK WHERE id =:id")
    Task getTaskById(int id);

    @Insert
    void insertAll(Task... tasks);

    @Insert
    void insert(Task task);

    @Delete
    void deleteAll(Task... tasks);

    @Delete
    void delete(Task task);

    @Query("DELETE FROM TASK WHERE isDone = 1")
    void deleteAllDoneTasks();

    @Update
    void update(Task task);
}
