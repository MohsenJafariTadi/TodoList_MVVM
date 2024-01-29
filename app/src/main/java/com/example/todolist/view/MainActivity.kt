package com.example.todolist.view

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.databinding.DialogAddTaskBinding
import com.example.todolist.databinding.DialogDeleteItemBinding
import com.example.todolist.databinding.DialogUpdateItemBinding
import com.example.todolist.model.AppDatabase
import com.example.todolist.model.MainViewModel
import com.example.todolist.model.task.Task
import com.example.todolist.model.task.TaskDao
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), TaskAdapter.TaskEvents {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUi()
        viewModel.getDataAlRep()

        binding.btnAddTask.setOnClickListener {

            val dialog = AlertDialog.Builder(this).create()
            val dialogBinding = DialogAddTaskBinding.inflate(layoutInflater)

            dialog.setView(dialogBinding.root)
            dialog.setCancelable(true)
            dialog.show()

            dialogBinding.dialogBtnDone.setOnClickListener {

                if (dialogBinding.dialogEdtTaskTitle.length() > 0 && dialogBinding.dialogEdtTaskContent.length() > 0) {
                    val title = dialogBinding.dialogEdtTaskTitle.text.toString()
                    val taskContent = dialogBinding.dialogEdtTaskContent.text.toString()
                    viewModel.insertData(Task(title = title, task = taskContent))
                    dialog.dismiss()
                    viewModel.getDataAlRep()
                } else {

                    Toast.makeText(this, "لطفا همه مقادیر را وارد کنید :)", Toast.LENGTH_SHORT)
                        .show()

                }
            }

        }


    }

    private fun viewModelInit() {
//        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }


    private fun initUi() {
        viewModelInit()
        getDataFromRoom()
    }

    private fun getDataFromRoom() {
        viewModel.getAllData.observe(this) {
            showDataInRecycler(it)
        }
    }

    private fun showDataInRecycler(data: List<Task>) {
        val taskAdapter = TaskAdapter(data, this)
        binding.recyclerMain.adapter = taskAdapter
        binding.recyclerMain.layoutManager =StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
    }


    override fun onTaskClicked(task: Task, position: Int) {

        val dialog = AlertDialog.Builder(this).create()
        val dialogUpdateBinding = DialogUpdateItemBinding.inflate(layoutInflater)
        dialogUpdateBinding.dialogEdtTaskTitle.setText(task.title)
        dialogUpdateBinding.dialogEdtTaskContent.setText(task.task)
        dialog.setView(dialogUpdateBinding.root)
        dialog.setCancelable(true)
        dialog.show()

        dialogUpdateBinding.dialogUpdateBtnCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialogUpdateBinding.dialogUpdateBtnDone.setOnClickListener {

            if (dialogUpdateBinding.dialogEdtTaskTitle.length() > 0 && dialogUpdateBinding.dialogEdtTaskContent.length() > 0) {
                val title = dialogUpdateBinding.dialogEdtTaskTitle.text.toString()
                val taskContent = dialogUpdateBinding.dialogEdtTaskContent.text.toString()
                viewModel.updateData(Task(id = task.id, title = title, task = taskContent))
                dialog.dismiss()
            } else {

                Toast.makeText(this, "لطفا همه مقادیر را وارد کنید :)", Toast.LENGTH_SHORT)
                    .show()

            }

        }


    }

    override fun onTaskLongClicked(task: Task, position: Int) {

        val dialog = AlertDialog.Builder(this).create()
        val dialogDeleteBinding = DialogDeleteItemBinding.inflate(layoutInflater)
        dialog.setView(dialogDeleteBinding.root)
        dialog.setCancelable(true)
        dialog.show()

        dialogDeleteBinding.dialogBtnDeleteCancel.setOnClickListener {
            dialog.dismiss()
        }

        dialogDeleteBinding.dialogBtnDeleteSure.setOnClickListener {

            viewModel.delete(task)
            dialog.dismiss()

        }
    }


}


