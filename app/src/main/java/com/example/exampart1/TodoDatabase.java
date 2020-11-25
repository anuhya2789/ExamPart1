package com.example.exampart1;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {TodoItem.class}, version = 1)
public abstract class TodoDatabase extends RoomDatabase {
    public abstract TodoDao mTodoItemDao();
}
