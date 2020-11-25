package com.example.exampart1;


import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface TodoDao {

    @Query("SELECT * FROM todo_item")
    List<TodoItem> getAll();

    @Insert
    void insert(TodoItem todoItem);

    @Query("DELETE FROM todo_item WHERE item = :item")
    void delete(String item);
}

