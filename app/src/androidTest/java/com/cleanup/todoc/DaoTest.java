package com.cleanup.todoc;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class DaoTest {

    // FOR DATA
    private TodocDatabase database;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), TodocDatabase.class).allowMainThreadQueries().build();
    }

    @After
    public void closeDb() throws Exception{
        database.close();
    }

    // DATA SET FOR TEST
    private static long PROJECT_ID = 1;
    private static Project PROJECT_DEMO = new Project(PROJECT_ID, "Tartempion", 0xFFEADAD1);
    private static Project PROJECT_DEMOTWO = new Project(PROJECT_ID, "Trucmush", 0xFFB4CDBA);
    private static Task TASK_DEMO = new Task(PROJECT_ID, "Laver vitres", 1603374906);
    private static Task TASK_DEMOTWO = new Task(PROJECT_ID, "Laver sol", 1603374906);



    @Test
    public void getTaskWhenNoTaskInserted() throws InterruptedException{
        // TEST
        List<Task> tasks = LiveDataTestUtil.getValue(this.database.taskDao().getTasks());
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void insertAndGetProject() throws InterruptedException {
        this.database.projectDao().insertProject(PROJECT_DEMO);

        List<Project> projects = LiveDataTestUtil.getValue(this.database.projectDao().getProjects());
        assertTrue(projects.size() == 1);
    }

    @Test
    public void insertAndGetTask() throws InterruptedException{
        // Before adding demo project and demo tasks
        this.database.projectDao().insertProject(PROJECT_DEMO);
        this.database.taskDao().insertTask(TASK_DEMO);

        // TEST
        List<Task> tasks = LiveDataTestUtil.getValue(this.database.taskDao().getTasks());
        assertTrue(tasks.size() == 1);
    }


    @Test
    public void insertAndDeleteTask() throws InterruptedException {
        database.projectDao().insertProject(PROJECT_DEMO);
        database.taskDao().insertTask(TASK_DEMO);
        Task taskAdded = LiveDataTestUtil.getValue(database.taskDao().getTasks()).get(0);
        database.taskDao().deleteTask(taskAdded);

        List<Task> tasks = LiveDataTestUtil.getValue(database.taskDao().getTasks());
        assertTrue(tasks.isEmpty());
    }


}
