package com.example.todolist.model.task

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int?=null,


    val title: String,
    val task: String,
)
