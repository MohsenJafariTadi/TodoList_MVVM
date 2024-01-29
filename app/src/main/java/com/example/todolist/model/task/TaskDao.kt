package com.example.todolist.model.task

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTaskOrUpdate(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Update
    fun update(task: Task)

    @Query("Select * FROM tasks_table")
    fun getAllData(): LiveData<List<Task>>
}

