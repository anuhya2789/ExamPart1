package com.example.exampart1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TodoDatabase mTodoDatabase;
    private ListView mListView;
    private ArrayAdapter mArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.list_todo);
        mTodoDatabase = Room.databaseBuilder(getApplicationContext(),
                TodoDatabase.class, "exam").allowMainThreadQueries().build();
        this.populateData();
    }

    private void populateData() {
        List<TodoItem> list = mTodoDatabase.mTodoItemDao().getAll();
        ArrayList<String> taskList = new ArrayList<>();
        for (TodoItem item : list) {
            taskList.add(item.item);
        }
        if (mArrayAdapter == null) {
            mArrayAdapter = new ArrayAdapter<>(this,
                    R.layout.item,
                    R.id.task_title,
                    taskList);
            mListView.setAdapter(mArrayAdapter);
        } else {
            mArrayAdapter.clear();
            mArrayAdapter.addAll(taskList);
            mArrayAdapter.notifyDataSetChanged();
        }
    }

    public void deleteTask(View view) {
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.task_title);
        String task = String.valueOf(taskTextView.getText());
        mTodoDatabase.mTodoItemDao().delete(task);
        populateData();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add_task:
                final EditText taskEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Add a new task")
                        .setMessage("What do you want to do next?")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(taskEditText.getText());
                                TodoItem todoItem = new TodoItem(task);
                                mTodoDatabase.mTodoItemDao().insert(todoItem);
                                populateData();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}