package com.cleanup.todoc.repositories;

import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.database.ProjectDao;
import com.cleanup.todoc.database.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskDataRepository {

    private final TaskDao taskDao;
    private Task task;

    public TaskDataRepository( TaskDao taskDao){
        this.taskDao = taskDao;
    }


    // --- GET ---
    public LiveData<List<Task>> getTask() {return this.taskDao.getTasks();}

    // --- CREATE ---
    public void creatTask(Task task) {taskDao.insertTask(task);}

    // --- DELETE ---
    public void deleteTask() {taskDao.deleteTask(task);}

}
