package com.example.exampart1;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo_item")
public class TodoItem {
    @PrimaryKey(autoGenerate = true)
    public long uid;
    @ColumnInfo(name = "item")
    public String item;
    public TodoItem(String item){
        this.item = item;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}

