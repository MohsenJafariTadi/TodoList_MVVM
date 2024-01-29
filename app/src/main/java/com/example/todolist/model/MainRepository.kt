package com.example.todolist.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.todolist.model.task.Task
import com.example.todolist.model.task.TaskDao
import com.example.todolist.view.MainActivity
import javax.inject.Inject

class MainRepository @Inject constructor (private val appDao:TaskDao) {

     //val taskDao: TaskDao = AppDatabase.getInstance(MainActivity.context!!).getTaskDao()

//    var _tasks = MutableLiveData<List<Task>>()
//    val  task: LiveData<List<Task>>
//        get()=_tasks

    var getAllData : LiveData<List<Task>> = appDao.getAllData()

//    fun getAllData(){
//         _tasks.postValue(taskDao.getAllData().value)
//    }
    fun insertTask(task: Task){
        return appDao.insertTaskOrUpdate(task)
    }
    fun updateTask(task: Task){
        return appDao.update(task)
    }
    fun removeTask(task: Task){
        return appDao.deleteTask(task)
    }

}