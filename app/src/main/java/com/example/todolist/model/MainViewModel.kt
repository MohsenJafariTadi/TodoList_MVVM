package com.example.todolist.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.model.task.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (private val repository: MainRepository):ViewModel() {
   // private val repository: MainRepository

    var getAllData : LiveData<List<Task>> = repository.getAllData

//    val getAllData: LiveData<List<Task>> =repository.getAllData()
//    val getAllData: LiveData<List<Task>> =repository.getAllData()

    fun  getDataAlRep(){
        viewModelScope.launch(Dispatchers.IO) {
//            getAllData = repository.getAllData()
        }
    }
    fun insertData(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertTask(task)
        }
    }
    fun updateData(task: Task){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateTask(task)
        }
    }
    fun delete(task: Task){
        viewModelScope.launch (Dispatchers.IO ){
            repository.removeTask(task)
        }
    }



}