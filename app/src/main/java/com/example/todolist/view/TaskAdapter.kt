package com.example.todolist.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.databinding.ActivityMainBinding
import com.example.todolist.databinding.ItemTaskBinding
import com.example.todolist.model.task.Task
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

class TaskAdapter(private val data: List<Task>, private val taskEvents:TaskEvents) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

//    lateinit var cardView: CardView


//     var colors= arrayOf<Int>(R.color.item2,R.color.item3)
    inner class TaskViewHolder(itemView: View,private val context: Context)
        : RecyclerView.ViewHolder(itemView) {
        val titletask=itemView.findViewById<TextView>(R.id.item_txt_title)
        val txttask=itemView.findViewById<TextView>(R.id.item_txt_task)


        val taskid=itemView
        @SuppressLint("SetTextI18n")
        fun bindData(position: Int) {
            titletask.text = data[position].title
            txttask.text = data[position].task
            taskid.id= data[position].id!!




            itemView.setOnClickListener {

                taskEvents.onTaskClicked(data[adapterPosition], adapterPosition)

            }

            itemView.setOnLongClickListener {

             taskEvents.onTaskLongClicked(data[adapterPosition],adapterPosition)

                true
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
//        binding= ItemTaskBinding.inflate(LayoutInflater)
        val itemList= LayoutInflater.from(parent.context).inflate(R.layout.item_task,parent,false)
        val holder=TaskViewHolder(itemList,parent.context)



//        cardView.setCardBackgroundColor()

        return  holder
    }



    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bindData(position)
    }

    override fun getItemCount(): Int {
       return data.size
    }



    interface TaskEvents {
        fun onTaskClicked(task: Task, position: Int)
        fun onTaskLongClicked(task: Task, position: Int)

    }
}



