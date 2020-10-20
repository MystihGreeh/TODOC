package com.cleanup.todoc.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel {

    // REPOSITORIES
    private final TaskDataRepository taskDataSource;
    private final ProjectDataRepository projectDataSource;
    private final Executor executor;

    // DATA
    @Nullable
    private LiveData<List<Project>> currentProject;

    public TaskViewModel(TaskDataRepository taskDataSource, ProjectDataRepository projectDataSource, Executor executor) {
        this.taskDataSource = taskDataSource;
        this.projectDataSource = projectDataSource;
        this.executor = executor;
    }

    public void init() {
        if (currentProject != null) {
            return;
        }
        currentProject = projectDataSource.getProjects();
    }

    // FOR PROJECT

    @Nullable
    public LiveData<List<Project>> getProjects() { return currentProject; }

    // FOR TASK

    public LiveData<List<Task>> getTasks() { return taskDataSource.getTask(); }

    public void createTask(Task task) { executor.execute(() -> taskDataSource.creatTask(task)); }

    public void deleteTask(Task task) { executor.execute(taskDataSource::deleteTask); }

}
