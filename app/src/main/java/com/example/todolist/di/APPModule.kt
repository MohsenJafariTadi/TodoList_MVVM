package com.example.todolist.di

import android.content.Context
import androidx.room.Room
import com.example.todolist.model.AppDatabase
import com.example.todolist.model.task.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object APPModule {

//    @Singleton
//    @Provides
//    fun getAppDB(@ApplicationContext context: Context):AppDatabase{
//        return AppDatabase.getInstance(context )
//    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app_dataBase.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }


    @Singleton
    @Provides
    fun getDao(appDB: AppDatabase): TaskDao {
        return appDB.getTaskDao()
    }


}