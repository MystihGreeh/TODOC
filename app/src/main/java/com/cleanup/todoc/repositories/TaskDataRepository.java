package com.cleanup.todoc.repositories;

import android.arch.lifecycle.LiveData;

import com.cleanup.todoc.database.ProjectDao;
import com.cleanup.todoc.database.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskDataRepository {

    private final TaskDao taskDao;

    public TaskDataRepository( TaskDao taskDao){
        this.taskDao = taskDao;
    }


    // --- GET ---
    public LiveData<List<Task>> getTask(long projectId) {return this.taskDao.getTask(projectId);}

    // --- CREATE ---
    public void creatTask(Task task) {taskDao.insertTask(task);}

    // --- DELETE ---
    public void deleteTask(long taskId) {taskDao.deleteTask(taskId);}

}
