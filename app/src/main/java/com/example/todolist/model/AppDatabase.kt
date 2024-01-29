package com.example.todolist.model
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todolist.model.task.TaskDao
import com.example.todolist.model.task.Task

@Database(
    entities = [Task::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTaskDao(): TaskDao
}